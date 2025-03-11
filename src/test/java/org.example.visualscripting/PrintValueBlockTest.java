package org.example.visualscripting;

import org.example.visualscripting.blocks.PrintValueBlock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class PrintValueBlockTest {

    @ParameterizedTest
    @ValueSource(ints = {10, -5, 0, 42})  // Тестуємо різні значення
    void testPrintValueBlock(int value) {
        // Зберігаємо оригінальний System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Підміняємо System.out

        try {
            PrintValueBlock block = new PrintValueBlock();
            block.action(value);  // Викликаємо метод, що друкує у консоль

            // Отримуємо текст, який вивівся в консоль
            String printedText = outputStream.toString().trim();
            assertEquals(String.valueOf(value), printedText,
                    () -> "Expected printed value: " + value + ", but got: " + printedText);
        } finally {
            System.setOut(originalOut);  // Відновлюємо стандартний вивід
        }
    }
}
