package org.example.visualscripting.blocks;

import java.util.Scanner;

public class InputValueBlock implements Block<Integer> {

    @Override
    public String getName() {
        return "inputValue";
    }

    @Override
    public Integer action(Object... inputs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value: ");
        return scanner.nextInt();
    }
}
