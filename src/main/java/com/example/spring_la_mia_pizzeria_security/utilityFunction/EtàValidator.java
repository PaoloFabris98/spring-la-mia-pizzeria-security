package com.example.spring_la_mia_pizzeria_security.utilityFunction;

import java.time.LocalDate;
import java.time.Period;

public class EtàValidator {

    public static Boolean età(LocalDate dataNascita) throws IllegalArgumentException {
        if (dataNascita == null) {
            throw new IllegalArgumentException("Data di nascita non valida.");
        }

        LocalDate today = LocalDate.now();
        Period period = Period.between(dataNascita, today);

        if (period.getYears() > 18
                || (period.getYears() == 18 && (today.getMonthValue() > dataNascita.getMonthValue() ||
                        (today.getMonthValue() == dataNascita.getMonthValue()
                                && today.getDayOfMonth() >= dataNascita.getDayOfMonth())))) {
            return true;
        }
        return false;
    }
}
