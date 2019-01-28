package ru.geekbrains.classes.matrix;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loader {
    public static String[][] load(String pathToFile) {
        String jsonString;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
            jsonString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Target file is not found");
        }

        Moshi moshi = new Moshi.Builder().build();

        Type numberMatrix = Types.newParameterizedType(List.class, Types.newParameterizedType(List.class, String.class));
        JsonAdapter<List<List<String>>> adapter = moshi.adapter(numberMatrix);
        List<List<String>> matrix = new ArrayList<>();
        try {
            matrix = adapter.fromJson(jsonString);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String[][] result = new String[matrix.size()][];

        int i = 0;
        for (List<String> numbers : matrix) {
            result[i++] = numbers.toArray(new String[numbers.size()]);
        }

        return result;
    }
}
