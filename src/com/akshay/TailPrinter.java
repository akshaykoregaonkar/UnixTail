package com.akshay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class TailPrinter {
    private int position = 0;
    private final int n;
    private String[] lines;
    private final List<Path> paths;

    public TailPrinter(List<Path> paths) {
        this.paths = paths;
        this.n = 10;
    }

    public TailPrinter(List<Path> paths, int n) {
        this.paths = paths;
        this.n = n;
    }

    private void getTailLines(String line) {
        lines[position % n] = line;
        position++;
    }

    public void printTail() {
        paths.forEach(this::printCurrentTail);
    }

    private void printCurrentTail(Path path) {
        lines = new String[n];
        position = 0;
        if (paths.size() > 1)
            System.out.println(path.getFileName() +" :");

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