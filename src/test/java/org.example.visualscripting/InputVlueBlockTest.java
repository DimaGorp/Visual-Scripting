package org.example.visualscripting;

import org.example.visualscripting.blocks.InputValueBlock;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class InputValueBlockTest {

    @Test
    void testInputValueBlock() {
        // Емуляція введення користувача (наприклад, 42)
        String simulatedInput = "42\n";
        InputStream originalSystemIn = System.in; // Зберігаємо оригінальний потік вводу
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes())); // Замінюємо на наш потік

        try {
            InputValueBlock block = new InputValueBlock();
            int result = block.action(); // Викликаємо метод, який читає з System.in
            assertEquals(42, result, "Expected input value to be 42");
        } finally {
            System.setIn(originalSystemIn); // Відновлюємо стандартний ввід
        }
    }
}
