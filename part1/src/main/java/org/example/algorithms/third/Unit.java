package org.example.algorithms.third;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Unit {
    String name;
    Map<Unit, Double> conversionRates = new HashMap<>();

    Unit(String name) {
        this.name = name;
    }

    void addConversion(Unit unit, double rate) {
        conversionRates.put(unit, rate);
    }

    double convertTo(Unit target, Set<Unit> visited) {
        if (this == target) return 1.0;
        visited.add(this);

        for (Map.Entry<Unit, Double> entry : conversionRates.entrySet()) {
            Unit nextUnit = entry.getKey();
            if (!visited.contains(nextUnit)) {
                double rate = entry.getValue() * nextUnit.convertTo(target, visited);
                if (rate > 0) return rate;
            }
        }

        return -1;
    }
}