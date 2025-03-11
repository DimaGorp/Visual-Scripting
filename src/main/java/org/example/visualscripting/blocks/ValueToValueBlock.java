package org.example.visualscripting.blocks;

public class ValueToValueBlock implements Block<Integer> {

    @Override
    public String getName() {
        return "valueToValue";
    }

    @Override
    public Integer action(Object... inputs) {
        return (int) inputs[0];  // Возвращаем значение из первого входа
    }
}
