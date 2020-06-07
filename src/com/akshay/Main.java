package com.akshay;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("large_sample.txt"))) {

                long count = 1;
                while (count != 100_000_001) {
                    writer.write("line "+ count++);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
