package com.example.spring_la_mia_pizzeria_security.utilityFunction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static String emailValidator(String email) throws IllegalArgumentException {
        Pattern emailPattern = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.find()) {
            throw new IllegalArgumentException("Email non valida");
        }
        return email;
    }
}
