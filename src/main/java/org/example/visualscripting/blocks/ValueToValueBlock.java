package org.example.visualscripting.blocks;

import java.util.UUID;

public class ValueToValueBlock implements Block<Integer> {

    private final String id;

    public ValueToValueBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "valueToValue";
    }

    @Override
    public String getId() {
        return id;
    }

}
