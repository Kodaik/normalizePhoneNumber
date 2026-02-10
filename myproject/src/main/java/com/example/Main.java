package com.example;
import java.util.Set;

public class Main {

    private static final Set<String> COUNTRY_CODES = Set.of(
        "387","381"
    );

    public static void main(String[] args) {

        String[] tests = {
            "065921456",
            "065 921 456",
            "065/921-456",
            "+381642661410",
            "381065090700"
        };

        for (String t : tests) {
            System.out.println("Input:  " + t);
            System.out.println("Output: " + normalizePhoneNumber(t));
            System.out.println();
        }
    }

    public static String normalizePhoneNumber(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        // 1. Remove everything except digits
        String digits = input.replaceAll("\\D", "");

        // 2. Convert 00 international prefix
        if (digits.startsWith("00")) {
            digits = digits.substring(2);
        }

        String countryCode = detectCountryCode(digits);
        String rest;

        if (countryCode != null) {
            // International number
            rest = digits.substring(countryCode.length());

            // Remove trunk 0 after country code (international rule)
            if (rest.startsWith("0")) {
                rest = rest.substring(1);
            }
        } else {
            // Local number → assume Bosnia
            countryCode = "387";
            rest = digits.startsWith("0") ? digits.substring(1) : digits;
        }

        // Bosnia-specific extra safety
        if (countryCode.equals("387") && rest.startsWith("0")) {
            rest = rest.substring(1);
        }

        return "+" + countryCode + rest;
    }

    private static String detectCountryCode(String digits) {
        for (int len = 3; len >= 1; len--) {
            if (digits.length() >= len) {
                String prefix = digits.substring(0, len);
                if (COUNTRY_CODES.contains(prefix)) {
                    return prefix;
                }
            }
        }
        return null;
    }
}
