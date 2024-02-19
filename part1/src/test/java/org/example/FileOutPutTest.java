package org.example;

import org.example.algorithms.second.FileOutPut;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class FileOutPutTest {

    @Test
    void testFileOutPutThread() throws InterruptedException, IOException {
        // Define the expected final value
        int expectedFinalValue = 10; // Assuming n = 100

        // Call the method under test
        FileOutPut.FileOutPutThread(10);

        // Read the content of the output file
        String content = new String(Files.readAllBytes(Paths.get("out.txt")));

        // Check if the file content equals the expected final value
        assertEquals(String.valueOf(expectedFinalValue), content.trim());

        // Check if the output file exists
        assertTrue(Files.exists(Paths.get("out.txt")));
    }
}