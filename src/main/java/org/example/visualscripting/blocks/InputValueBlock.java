package org.example.visualscripting.blocks;

import java.util.Scanner;
import java.util.UUID;

public class InputValueBlock implements Block<Integer> {

    private final String id;

    public InputValueBlock() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public String getName() {
        return "inputValue";
    }

    @Override
    public String getId() {
        return id;
    }


}
