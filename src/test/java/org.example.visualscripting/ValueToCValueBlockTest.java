package org.example.visualscripting;

import org.example.visualscripting.blocks.ValueToCValueBlock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ValueToCValueBlockTest {

    @ParameterizedTest
    @ValueSource(ints = {10, -5, 0, 42, 100})  // Тестуємо різні константи
    void testValueToCValueBlock(int constantValue) {
        ValueToCValueBlock block = new ValueToCValueBlock(constantValue);
        assertEquals(constantValue, block.action(),
                () -> "Expected constant value: " + constantValue + ", but got: " + block.action());
    }
}
