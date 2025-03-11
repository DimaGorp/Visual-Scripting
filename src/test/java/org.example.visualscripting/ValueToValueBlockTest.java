package org.example.visualscripting;

import org.example.visualscripting.blocks.ValueToValueBlock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ValueToValueBlockTest {

    @ParameterizedTest
    @ValueSource(ints = {10, -5, 0, 42, 100})  // Тестуємо різні вхідні значення
    void testValueToValueBlock(int inputValue) {
        ValueToValueBlock block = new ValueToValueBlock();
        assertEquals(inputValue, block.action(inputValue),
                () -> "Expected value: " + inputValue + ", but got: " + block.action(inputValue));
    }
}
