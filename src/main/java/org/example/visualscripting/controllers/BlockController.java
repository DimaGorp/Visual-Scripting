package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockController {
    private List<Block> blocks;

    public BlockController() {
        blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        System.out.println(block.getName());
        blocks.add(block);
    }


    public List<Block> getBlocks() {
        return blocks;
    }


    public void saveToJson(String filename) throws IOException {
        JsonController jsonController = new JsonController();
        jsonController.saveBlocksToJson(filename, blocks);
    }
}
