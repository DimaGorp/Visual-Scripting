package org.example.visualscripting.blocks;

import java.util.UUID;

public class ValueToCValueBlock implements Block<Integer> {
    private int constantValue;
    private String id;
    public ValueToCValueBlock(int constantValue) {
        this.constantValue = constantValue;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return "valueToCValue";
    }

    @Override
    public String getId() {
        return id;
    }


}
