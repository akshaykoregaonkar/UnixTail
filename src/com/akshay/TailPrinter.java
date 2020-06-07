package com.akshay;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class TailPrinter {
    private int position = 0;
    private final int n;
    private final String[] lines;
    private final Path path;

    public TailPrinter(Path path) {
        this.path = path;
        this.n = 10;
        this.lines = new String[n];
    }

    public TailPrinter(Path path, int n) {
        this.path = path;
        this.n = n;
        this.lines = new String[n];
    }

    private void getTailLines(String line) {
        lines[position % n] = line;
        position++;
    }

    public void printTail()  {
        // Files.lines requires a try-with-resources to ensures resource is closed after a terminal stream operation
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> getTailLines(line));
            Arrays.asList(lines)
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}