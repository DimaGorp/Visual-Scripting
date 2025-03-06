package org.example.visualscripting.blocks;

public class PrintValueBlock implements Block<Void> {

    @Override
    public String getName() {
        return "printValue";
    }

    @Override
    public Void action(Object... inputs) {
        int value = (int) inputs[0];  // Получаем значение V
        System.out.println(value);  // Печатаем значение на экране
        return null;
    }
}
