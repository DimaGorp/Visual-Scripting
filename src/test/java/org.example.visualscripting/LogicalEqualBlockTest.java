package org.example.visualscripting;

import org.example.visualscripting.blocks.LogicalEqualBlock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class LogicalEqualBlockTest {

    @ParameterizedTest
    @CsvSource({
            "10, 10, true",  // Тест коли значення рівні (10 == 10)
            "5, 10, false",  // Тест коли значення не рівні (5 != 10)
            "-3, -3, true",  // Тест з від'ємними числами (-3 == -3)
            "0, 0, true",    // Тест з нулями (0 == 0)
            "100, 99, false" // Тест з майже рівними значеннями (100 != 99)
    })
    void testLogicalEqualBlock(int value, int constant, boolean expectedResult) {
        LogicalEqualBlock block = new LogicalEqualBlock();
        assertEquals(expectedResult, block.action(value, constant),
                () -> "Failed for value=" + value + ", constant=" + constant);
    }
}
