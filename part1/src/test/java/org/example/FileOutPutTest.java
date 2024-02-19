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

        int expectedFinalValue = 10; // Assuming n = 100


        FileOutPut.FileOutPutThread(10);

        String content = new String(Files.readAllBytes(Paths.get("out.txt")));


        assertEquals(String.valueOf(expectedFinalValue), content.trim());


        assertTrue(Files.exists(Paths.get("out.txt")));
    }
}