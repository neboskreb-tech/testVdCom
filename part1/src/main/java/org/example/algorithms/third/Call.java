package org.example.algorithms.third;

import java.util.Scanner;

public class Call {
    public static void Call( String[] args) {
        Scanner scanner = new Scanner(System.in);
        UnitConverter converter = new UnitConverter();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;

            String[] parts = line.split(" = ");
            String[] leftParts = parts[0].split(" ");
            String[] rightParts = parts[1].split(" ");

            if (rightParts[0].equals("?")) {
                System.out.println(converter.convert(leftParts[1], Double.parseDouble(leftParts[0]), rightParts[1]));
            } else {
                converter.addConversion(leftParts[1], Double.parseDouble(leftParts[0]), rightParts[1], Double.parseDouble(rightParts[0]));
            }
        }

        scanner.close();
    }
}

