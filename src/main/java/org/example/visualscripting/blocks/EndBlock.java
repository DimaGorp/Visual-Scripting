package org.example.visualscripting.blocks;


import java.util.UUID;

public class EndBlock implements Block{

    private final String id;

    public EndBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "end";
    }

    @Override
    public String getId() {
        return id;
    }

}
