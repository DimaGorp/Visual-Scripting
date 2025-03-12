package org.example.visualscripting.controllers;

import org.example.visualscripting.blocks.Block;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonController {


    public void saveBlocksToJson(String filename, List<Block> blocks) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filename), blocks);
    }


    public List<Block> loadBlocksFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), objectMapper.getTypeFactory().constructCollectionType(List.class, Block.class));
    }
}
