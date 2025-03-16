package org.example.visualscripting.blocks;

import java.util.UUID;

public class LogicalEqualBlock implements Block<Boolean> {

    private final String id;

    public LogicalEqualBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "logicalEqual";
    }

    @Override
    public String getId() {
        return id;
    }


}
