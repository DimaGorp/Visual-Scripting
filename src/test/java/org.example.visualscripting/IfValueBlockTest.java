package org.example.visualscripting;

import org.example.visualscripting.blocks.IfValueBlock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class IfValueBlockTest {

    @ParameterizedTest
    @CsvSource({
            "equal, 10, 10, true",
            "equal, 10, 5, false",
            "less, 10, 5, true",
            "less, 10, 15, false",
            "invalid, 10, 10, false"
    })
    void testIfValueBlock(String comparisonType, int comparisonValue, int inputValue, boolean expectedResult) {
        IfValueBlock block = new IfValueBlock(comparisonType, comparisonValue);
        assertEquals(expectedResult, block.action(inputValue),
                () -> "Failed for comparisonType=" + comparisonType + ", comparisonValue=" + comparisonValue + ", inputValue=" + inputValue);
    }
}
