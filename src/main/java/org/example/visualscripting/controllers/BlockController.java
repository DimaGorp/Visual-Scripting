package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blockmanager.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockController {
    private List<Block> blocks;
    BlockManager bm;

    public BlockController() {
        blocks = new ArrayList<>();
        bm = new BlockManager();
    }

    public void addBlock(Block block) {
        System.out.println(block.getName());
        if(bm.Add(block)){
            bm.Print();
            System.out.println("-------------------------------------------------------------");
        }
        //blocks.add(block);
    }


    public List<Block> getBlocks() {
        return blocks;
    }


    public void saveToJson(String filename) throws IOException {
        JsonController jsonController = new JsonController();
        jsonController.saveBlocksToJson(filename, blocks);
    }
}
