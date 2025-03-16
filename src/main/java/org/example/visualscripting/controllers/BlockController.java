package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blocks.StartBlock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockController {
    private List<Block> blocks;

    private Map<Integer, Block> blockMap = new HashMap<>();
    private int blockCount = 0;

    {
        Block startBlock = new StartBlock();
        blockMap.put(0, startBlock);
    }

    public BlockController() {
        blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        System.out.println(block.getName());
        blocks.add(block);
    }

    public void blockConnection(Block block) {

        blockCount++;
        blockMap.put(blockCount, block);

    }

    public List<Block> getBlocks() {
        return blocks;
    }


    public void saveToJson(String filename) throws IOException {
        JsonController jsonController = new JsonController();
        jsonController.saveBlocksToJson(filename, blocks);
    }
}
