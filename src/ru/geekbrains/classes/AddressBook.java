package ru.geekbrains.classes;

import java.util.*;

class AddressBook {
    Map<String, Set<String>> people = new HashMap<>();

    AddressBook add(String lastName, String phone) {
        people.merge(
            lastName,
            new HashSet<>(Arrays.asList(phone)),
            (a, b) -> {
                a.addAll(b);
                return a;
            });
        return this;
    }

    String[] get(String lastName) {
        return people.get(lastName).toArray(new String[] {});
    }
}
