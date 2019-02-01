package ru.geekbrains.classes;

import java.util.*;

class StringList {
    private List<String> list;

    StringList(String... array) {
        list = Arrays.asList(array);
    }

    String[] getUnique() {
        Set<String> unique = new HashSet<>(list);
        return unique.toArray(new String[]{});
    }

    Map<String, Integer> getUniqueCount() {
        Map<String, Integer> uniqueCount = new HashMap<>();
        for (String item : list) {
            uniqueCount.merge(item, 1, (a, b) -> a + b);
        }
        return uniqueCount;
    }
}
