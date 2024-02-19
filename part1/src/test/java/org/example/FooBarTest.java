package org.example;
import org.example.algorithms.first.FooBar;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FooBarTest{

        @Test
        void testPrintFooBar() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            FooBar.printFooBar(15);

            String expectedOutput = "1\r\n" +
                    "2\r\n" +
                    "Foo\r\n" +
                    "4\r\n" +
                    "Bar\r\n" +
                    "Foo\r\n" +
                    "7\r\n" +
                    "8\r\n" +
                    "Foo\r\n" +
                    "Bar\r\n" +
                    "11\r\n" +
                    "Foo\r\n" +
                    "13\r\n" +
                    "14\r\n" +
                    "FooBar\r\n";
            assertEquals(expectedOutput, outContent.toString());
        }

        @Test
        void testPrintFooBarTwo() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            FooBar.printFooBarTwo(15);

            String expectedOutput = "1\r\n" +
                    "2\r\n" +
                    "Foo\r\n" +
                    "4\r\n" +
                    "Bar\r\n" +
                    "Foo\r\n" +
                    "7\r\n" +
                    "8\r\n" +
                    "Foo\r\n" +
                    "Bar\r\n" +
                    "11\r\n" +
                    "Foo\r\n" +
                    "13\r\n" +
                    "14\r\n" +
                    "FooBar\r\n";
            assertEquals(expectedOutput, outContent.toString());
        }

        @Test
        void testPrintFooBarHeadOn() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            FooBar.printFooBarHeadOn(15);

            String expectedOutput = "1\r\n" +
                    "2\r\n" +
                    "Foo\r\n" +
                    "4\r\n" +
                    "Bar\r\n" +
                    "Foo\r\n" +
                    "7\r\n" +
                    "8\r\n" +
                    "Foo\r\n" +
                    "Bar\r\n" +
                    "11\r\n" +
                    "Foo\r\n" +
                    "13\r\n" +
                    "14\r\n" +
                    "FooBar\r\n";
            assertEquals(expectedOutput, outContent.toString());
        }

        @Test
        void testRecursiveFooBar() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            FooBar.recursiveFooBar(1, 15);

            String expectedOutput = "1\r\n" +
                    "2\r\n" +
                    "Foo\r\n" +
                    "4\r\n" +
                    "Bar\r\n" +
                    "Foo\r\n" +
                    "7\r\n" +
                    "8\r\n" +
                    "Foo\r\n" +
                    "Bar\r\n" +
                    "11\r\n" +
                    "Foo\r\n" +
                    "13\r\n" +
                    "14\r\n" +
                    "FooBar\r\n";
            assertEquals(expectedOutput, outContent.toString());
        }
}
