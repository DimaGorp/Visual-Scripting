package org.example.visualscripting.blocks;

public class ValueToCValueBlock implements Block<Integer> {
    private int constantValue;

    public ValueToCValueBlock(int constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public String getName() {
        return "valueToCValue";
    }


}
