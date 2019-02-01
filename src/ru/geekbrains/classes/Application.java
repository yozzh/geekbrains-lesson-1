package ru.geekbrains.classes;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class Application {
    private static Logger log = Logger.getLogger("Application");
    public static void main(String[] args) {
        task1();
        task2();
    }

    static void task1() {
        StringList stringList = new StringList("a", "b", "c", "c", "d", "e", "f", "g", "h", "h", "i");
        String[] unique = stringList.getUnique();
        log.info(String.format("Unique elements are: %s", String.join(", ", unique)));

        Map<String, Integer> uniqueCount = stringList.getUniqueCount();
        for (Map.Entry<String, Integer> item: uniqueCount.entrySet()) {
            log.info(String.format("\"%s\": %d", item.getKey(), item.getValue()));
        }
    }

    static void task2() {
        AddressBook book = new AddressBook();
        book.add("Ivanov", "+12345678");
        book.add("Ivanov", "+12345678");
        book.add("Ivanov", "+12345679");
        book.add("Smirnov", "+12345679");
        book.add("Sobolev", "+12345679");
        for (Map.Entry<String, Set<String>> address : book.people.entrySet()) {
            // Synthetic logic to test AddressBook::get method
            String lastName = address.getKey();
            String[] phones = book.get(lastName);
            log.info(lastName);
            log.info(String.join(", ", phones));
        }
    }
}
