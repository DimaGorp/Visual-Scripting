package org.example.visualscripting.blockmanager;

import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blocks.EndBlock;
import org.example.visualscripting.blocks.IfValueBlock;

import java.util.UUID;

public class BlockLogicalNode extends BlockNode {
    private BlockNode left;
    private String leftId;

    private BlockNode right;
    private String rightId;


    public BlockLogicalNode() {
        left = null;
        right = null;
        leftId = UUID.randomUUID().toString();
        rightId = UUID.randomUUID().toString();

    }

    public BlockLogicalNode(Block data) {
        this();
        this.data = data;
    }

    public String getLeftId(){
        return leftId;
    }
    public String getRightId(){
        return rightId;
    }

    void setLeft(BlockNode data,BlockNode end) {
        if(data.data instanceof EndBlock) {
            left = data;
            return;
        }
        left = data;
        left.next = end;

    }
    private BlockNode find(String id) {
        BlockNode current;
        for (current = left.next; !current.data.getId().equals(id); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        for (current = right.next; !current.data.getId().equals(id); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        return null;
    }

    void setRight(BlockNode data, BlockNode end) {
        if(data.data instanceof EndBlock) {
            right = data;
            return;
        }
        right = data;
        right.next = end;
    }

    BlockNode getLeft() {
        return left;
    }

    BlockNode getRight() {
        return right;
    }
    public boolean insert(Block<?> newBlock, String Id) throws NullPointerException {
        BlockNode current = find(Id);
        if(current == null) {
            return false;
        }
        BlockNode next = current.next.next;
        current.next = new BlockNode(newBlock);
        current.next.next = next;
        return true;
    }
}