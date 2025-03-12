package org.example.visualscripting.blockmanager;

import javafx.application.Application;
import javafx.stage.Stage;

import org.example.visualscripting.blocks.Block;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class BlockManager {
    private final Map<String, Block<?>> blockRegistry = new HashMap<>();

    // Constructor to auto-load blocks
    public BlockManager() {
        loadBlocks();
    }

    // Register a block manually
    public void registerBlock(Block<?> block) {
        blockRegistry.put(block.getName(), block);
    }

    // Retrieve a block by name
    public Block<?> getBlock(String name) {
        return blockRegistry.get(name);
    }

    // Load all blocks dynamically (optional, if using ServiceLoader)
    private void loadBlocks() {
        ServiceLoader<Block> loader = ServiceLoader.load(Block.class);
        for (Block<?> block : loader) {
            registerBlock(block);
        }
    }

    // Get all registered blocks
    public Map<String, Block<?>> getAllBlocks() {
        return blockRegistry;
    }
}
