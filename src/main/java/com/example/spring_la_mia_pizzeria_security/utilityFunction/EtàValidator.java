package com.example.spring_la_mia_pizzeria_security.utilityFunction;

import java.time.LocalDate;
import java.time.Period;

public class EtàValidator {
    public static Boolean età(LocalDate i) throws IllegalArgumentException {
        LocalDate timeNow = LocalDate.now();
        if (Period.between(timeNow, i).getYears() < 18) {
            return false;
        }
        return true;
    }
}
