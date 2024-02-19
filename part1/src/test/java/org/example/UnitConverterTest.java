package org.example;
import org.example.algorithms.third.Call;
import org.example.algorithms.third.UnitConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UnitConverterTest {
    private UnitConverter converter;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent = null;

    @Before
    public void setUp() {
        converter = new UnitConverter();
        // Инициализация конвертаций, используемых в тестах
        converter.addConversion("byte", 1024, "kilobyte", 1);
        converter.addConversion("bit", 8, "byte", 1);
        converter.addConversion("kilobyte", 1024, "megabyte", 1);
    }
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        if (inContent != null) {
            System.setIn(System.in);
        }
    }


    @Test
    public void testDirectConversion() {
        String result = converter.convert("byte", 2048, "kilobyte");
        assertEquals("2048 byte = 2 kilobyte", result);
    }

    @Test
    public void testIndirectConversion() {
        String result = converter.convert("bit", 16384, "kilobyte");
        assertEquals("16384 bit = 2 kilobyte", result);
    }

    @Test
    public void testNoConversionPath() {
        String result = converter.convert("byte", 1, "giraffe");
        assertEquals("Conversion not possible.", result);
    }

    @Test
    public void testReverseConversion() {
        String result = converter.convert("megabyte", 1, "byte");
        assertEquals("1 megabyte = 1048576 byte", result);
    }
    @Test
    public void testApplication() {
        String input = "1024 byte = 1 kilobyte\n" +
                "1 byte = 8 bit\n"+
                "2 kilobyte = ? bit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Call.Call(new String[]{});

        String expectedOutput = "2 kilobyte = 16384 bit";
        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
