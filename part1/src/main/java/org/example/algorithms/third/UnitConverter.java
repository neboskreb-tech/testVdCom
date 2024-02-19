package org.example.algorithms.third;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;



public class UnitConverter {
    private Map<String, Unit> units = new HashMap<>();

    private Unit getOrCreateUnit(String unitName) {
        return units.computeIfAbsent(unitName, Unit::new);
    }

    public void addConversion(String fromUnitName, double fromValue, String toUnitName, double toValue) {
        Unit fromUnit = getOrCreateUnit(fromUnitName);
        Unit toUnit = getOrCreateUnit(toUnitName);

        fromUnit.addConversion(toUnit, toValue / fromValue);
        toUnit.addConversion(fromUnit, fromValue / toValue);
    }

    public String convert(String fromUnitName, double fromValue, String toUnitName) {
        if (!units.containsKey(fromUnitName) || !units.containsKey(toUnitName)) {
            return "Conversion not possible.";
        }

        Unit fromUnit = units.get(fromUnitName);
        Unit toUnit = units.get(toUnitName);
        Set<Unit> visited = new HashSet<>();

        double conversionRate = fromUnit.convertTo(toUnit, visited);
        if (conversionRate < 0) {
            return "Conversion not possible.";
        }

        double result = fromValue * conversionRate;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#.#", symbols);

        return String.format("%s %s = %s %s", df.format(fromValue), fromUnitName, df.format(result), toUnitName);
    }

}
