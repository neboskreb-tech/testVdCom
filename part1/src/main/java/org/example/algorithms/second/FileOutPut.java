package org.example.algorithms.second;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;


public class FileOutPut {

    private static final String FILE_NAME = "out.txt";
    private static AtomicInteger value = new AtomicInteger(0);

        public static void FileOutPutThread(int n) throws InterruptedException {

            if (n <= 0 || n % 2 != 0) {
                System.out.println("Число должно быть больше 0 и кратно 2.");
                return;
            }

            // Создание и запуск потоков
            Thread thread1 = new Thread(() -> incrementValue(n / 2), "Поток 1");
            Thread thread2 = new Thread(() -> incrementValue(n / 2), "Поток 2");

            thread1.start();
            thread2.start();

            // Ожидание завершения потоков
            thread1.join();
            thread2.join();

            // Запись конечного значения в файл
            writeToFile(value.get());

            // Вывод конечного значения
            System.out.println("Конечное значение в файле: " + value.get());
        }

    private static void incrementValue(int increments) {
        for (int i = 0; i < increments; i++) {
            int oldValue = value.getAndIncrement();
            int newValue = oldValue + 1;
            System.out.println(Thread.currentThread().getName() + ": " + oldValue + " -> " + newValue);
        }
    }

    private static void writeToFile(int finalValue) {
        try {
            Files.write(Paths.get(FILE_NAME), String.valueOf(finalValue).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

