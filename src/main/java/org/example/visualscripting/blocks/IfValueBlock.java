package org.example.visualscripting.blocks;

import java.util.UUID;

public class IfValueBlock implements Block<Boolean> {

    private Comparison comparisonType;  // "equal" или "less"
    private int comparisonValue;    // Константа для сравнения
    private String id;

    public IfValueBlock(String comparisonType, int comparisonValue) {
        this.comparisonType = Comparison.valueOf(comparisonType);
        this.comparisonValue = comparisonValue;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return "ifValue";
    }

    @Override
    public String getId() {
        return id;
    }

    private enum Comparison{
        EQUAL, LESS, GREATER, ELESS, EGREATER
    }


}
