package org.example.visualscripting.blocks;

public interface Block<T> {
    String getName();
    T action(Object... inputs);

}

