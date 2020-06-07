package com.akshay.tests;

import com.akshay.TailPrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TailPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printAllLinesOfExtraSmallFileWhenNExceedsFileSize() {
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("line 1");
        printWriter.println("line 2");
        printWriter.println("line 3");
        printWriter.println("line 4");
        printWriter.println("line 5");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        TailPrinter tailPrinter = new TailPrinter(getPath("extra_small_sample.txt"), 10);
        tailPrinter.printTail();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printLastLineForSmallFile() {
        TailPrinter tailPrinter = new TailPrinter(getPath("small_sample.txt"), 1);
        tailPrinter.printTail();
        assertEquals("line 20\r\n", outContent.toString());
    }

    @Test
    void printSmallFileWithDefaultSetting() {
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("line 11");
        printWriter.println("line 12");
        printWriter.println("line 13");
        printWriter.println("line 14");
        printWriter.println("line 15");
        printWriter.println("line 16");
        printWriter.println("line 17");
        printWriter.println("line 18");
        printWriter.println("line 19");
        printWriter.println("line 20");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        TailPrinter tailPrinter = new TailPrinter(getPath("small_sample"), 5);
        tailPrinter.printTail();

        assertEquals(expected, outContent.toString());
    }

/*

    // Use the code in the Main function to generate large_sample.txt and extra_large_sample.txt

    @Test
    void printLast5LinesOfLargeFile() {
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("line 999996");
        printWriter.println("line 999997");
        printWriter.println("line 999998");
        printWriter.println("line 999999");
        printWriter.println("line 1000000");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        TailPrinter tailPrinter = new TailPrinter(getPath("large_sample.txt"), 5);
        tailPrinter.printTail();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printLast5LinesOfExtraLargeFile() {
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.println("line 99999996");
        printWriter.println("line 99999997");
        printWriter.println("line 99999998");
        printWriter.println("line 99999999");
        printWriter.println("line 100000000");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        TailPrinter tailPrinter = new TailPrinter(getPath("extra_large_sample.txt"), 5);
        tailPrinter.printTail();

        assertEquals(expected, outContent.toString());
    }
*/

    private Path getPath(String filename){
        try {
            return Paths.get(TailPrinterTest.class.getResource(filename).toURI());
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}