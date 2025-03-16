package org.example.visualscripting.blocks;

public class IfValueBlock implements Block<Boolean> {
    private Comparison comparisonType;  // "equal" или "less"
    private int comparisonValue;    // Константа для сравнения

    public IfValueBlock(String comparisonType, int comparisonValue) {
        this.comparisonType = Comparison.valueOf(comparisonType);
        this.comparisonValue = comparisonValue;
    }

    @Override
    public String getName() {
        return "ifValue";
    }
    private enum Comparison{
        MORE, LESS
    }


}
