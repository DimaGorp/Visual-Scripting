package org.example.visualscripting.blocks;

import java.util.UUID;

public class StartBlock implements Block {
    private final String id;

    public StartBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getId() {
        return id;
    }


}
