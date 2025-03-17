package org.example.visualscripting.blockmanager;

import org.example.visualscripting.blocks.Block;

public class BlockNode {
    protected Block data;
    protected BlockNode next;

    public BlockNode() {
        data = null;
        next = null;
    }

    public BlockNode(Block data) {
        this();
        this.data = data;
    }
    public BlockNode(final BlockNode other){
        data = other.data;
        next = other.next;
    }
}