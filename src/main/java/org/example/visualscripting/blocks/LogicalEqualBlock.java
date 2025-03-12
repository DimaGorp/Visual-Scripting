package org.example.visualscripting.blocks;

public class LogicalEqualBlock implements Block<Boolean> {

    @Override
    public String getName() {
        return "logicalEqual";
    }

    @Override
    public Boolean action(Object... inputs) {
        int value = (int) inputs[0];  // Получаем значение V
        int constant = (int) inputs[1];  // Получаем значение C
        return value == constant;  // Проверка V == C
    }
}
