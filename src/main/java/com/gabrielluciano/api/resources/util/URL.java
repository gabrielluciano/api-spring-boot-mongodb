package com.gabrielluciano.api.resources.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {

    private URL() {
    }

    public static LocalDate convertDate(String dateStr, LocalDate defaultValue) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, fmt);
        } catch (DateTimeParseException e) {
            return defaultValue;
        }
    }
}
