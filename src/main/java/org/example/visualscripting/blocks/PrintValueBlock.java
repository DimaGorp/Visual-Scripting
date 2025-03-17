package org.example.visualscripting.blocks;

import java.util.UUID;

public class PrintValueBlock implements Block<Void> {

    private final String id;

    public PrintValueBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "printValue";
    }

    @Override
    public String getId() {
        return id;
    }


}
