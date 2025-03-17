package org.example.visualscripting.JavaCodeGenerator;

import org.example.visualscripting.blockmanager.BlockManager;
import org.example.visualscripting.blocks.Block;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
public class JavaCodeGenerator {
    
    public void generateAllBlockLogic(List<Block> Blocks) {
        // Iterate over all blocks and generate code for each block
        for (Block<?> block : Blocks) {
            generateBlockLogic(block);
        }
    }

    private void generateBlockLogic(Block<?> block) {
        // Get the block name (cleaned of spaces)
        String blockName = block.getName().replaceAll("\\s+", "");

        // Generate the method body specific to the block type
        String methodBody = generateMethodBody(block);

        // Print out the generated code
        System.out.println("Generated code for " + blockName + ":");
        System.out.println(methodBody);
    }

    private String generateMethodBody(Block<?> block) {
        // Generate code based on the block type

        if (block instanceof org.example.visualscripting.blocks.IfValueBlock) {
            // Logic for IfValueBlock: generates an 'if' statement
            return "if (condition) {\n" +
                    "    trueBranch.run();\n" +
                    "} else {\n" +
                    "    falseBranch.run();\n" +
                    "}";

        }

        if (block instanceof org.example.visualscripting.blocks.InputValueBlock) {
            // Logic for InputValueBlock: returns the first input value
            return "return inputs[0];";  // Just returns the first input

        }

        if (block instanceof org.example.visualscripting.blocks.LogicalEqualBlock) {
            // Logic for LogicalEqualBlock: compares two values
            return "return value1.equals(value2);";  // Compares two values for equality

        }

        if (block instanceof org.example.visualscripting.blocks.PrintValueBlock) {
            // Logic for PrintValueBlock: prints the value
            return "System.out.println(inputs[0]);";  // Prints the value of the first input

        }

        if (block instanceof org.example.visualscripting.blocks.ValueToCValueBlock) {
            // Logic for ValueToCValueBlock: returns a constant value (e.g., 1)
            return "return 1;";  // Just returns a constant value (1 in this case)

        }

        if (block instanceof org.example.visualscripting.blocks.ValueToValueBlock) {
            // Logic for ValueToValueBlock: returns the first input value
            return "return inputs[0];";  // Returns the first input (no transformation)

        }

        // Default case if no matching block type is found
        return "/* Unknown block type */";
    }
}
