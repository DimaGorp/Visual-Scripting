package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blocks.IfValueBlock;
import org.example.visualscripting.blocks.StartBlock;
import org.example.visualscripting.blockmanager.*;
import java.io.IOException;
import java.util.*;

public class BlockController {
    private List<Block> blocks;
    BlockManager bm;

    private Map<Integer, Block> blockMap = new HashMap<>();
    private int blockCount = 0;

    {
        Block startBlock = new StartBlock();
        blockMap.put(0, startBlock);
    }

    public BlockController() {
        blocks = new ArrayList<>();
        bm = new BlockManager();
    }

    public void addBlock(Block block) {
        /*bm.print();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        bm.insert(block, input);*/


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
