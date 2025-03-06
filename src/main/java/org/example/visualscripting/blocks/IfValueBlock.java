package org.example.visualscripting.blocks;

public class IfValueBlock implements Block<Boolean> {
    private String comparisonType;  // "equal" или "less"
    private int comparisonValue;    // Константа для сравнения

    public IfValueBlock(String comparisonType, int comparisonValue) {
        this.comparisonType = comparisonType;
        this.comparisonValue = comparisonValue;
    }

    @Override
    public String getName() {
        return "ifValue";
    }

    @Override
    public Boolean action(Object... inputs) {
        int value = (int) inputs[0];  // Получаем значение V из входных данных
        if ("equal".equals(comparisonType)) {
            return value == comparisonValue;  // Сравнение V == C
        } else if ("less".equals(comparisonType)) {
            return value < comparisonValue;  // Сравнение V < C
        }
        return false;
    }
}
