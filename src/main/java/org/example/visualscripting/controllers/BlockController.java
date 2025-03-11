package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;
import java.util.List;
import java.util.ArrayList;

public class BlockController {
    private List<Block> blocks;

    public BlockController() {
        blocks = new ArrayList<>();
    }


    public void addBlock(Block block) {
        blocks.add(block);
    }


    public void executeBlocks() {
        for (Block block : blocks) {
            System.out.println("Executing block: " + block.getName());
            block.action();
        }
    }
}
