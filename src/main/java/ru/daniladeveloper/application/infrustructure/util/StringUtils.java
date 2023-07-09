package ru.daniladeveloper.application.infrustructure.util;

import java.util.Collection;

public class StringUtils {

    public static boolean containsAnyOf(String origin, Collection<String> patterns) {
        return patterns.stream().anyMatch(origin::contains);
    }
}
