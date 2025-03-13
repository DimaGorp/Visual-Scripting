package org.example.visualscripting;

import org.example.visualscripting.JavaCodeGenerator.JavaCodeGenerator;
import org.example.visualscripting.blocks.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class JavaCodeGeneratorTest {

    private static Stream<Arguments> provideBlocksForTesting() {
        return Stream.of(
                Arguments.of(new IfValueBlock("equal", 10),
                        "if (condition) {\n" +
                                "    trueBranch.run();\n" +
                                "} else {\n" +
                                "    falseBranch.run();\n" +
                                "}"),

                Arguments.of(new InputValueBlock(), "return inputs[0];"),

                Arguments.of(new LogicalEqualBlock(), "return value1.equals(value2);"),

                Arguments.of(new PrintValueBlock(), "System.out.println(inputs[0]);"),

                Arguments.of(new ValueToCValueBlock(5), "return 1;"), // Код завжди повертає "1"

                Arguments.of(new ValueToValueBlock(), "return inputs[0];")
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlocksForTesting")
    void testGenerateMethodBody(Block<?> block, String expectedCode) {
        JavaCodeGenerator generator = new JavaCodeGenerator();
        String generatedCode = generator.generateMethodBody(block);
        assertEquals(expectedCode, generatedCode,
                () -> "Generated code for " + block.getClass().getSimpleName() + " is incorrect.");
    }
}
