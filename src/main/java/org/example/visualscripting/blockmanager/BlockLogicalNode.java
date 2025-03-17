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

    public BlockLogicalNode(Block data, BlockNode end) {
        this();
        left = end;
        right = end;
        this.data = data;
    }

    public String getLeftId(){
        return leftId;
    }
    public String getRightId(){
        return rightId;
    }

    void setLeft(BlockNode data) {
        BlockNode next = null;
        if(left.data instanceof EndBlock){
            next = new BlockNode(left);
        }else {
            next = left;
        }
        left = new BlockNode(data.data);
        left.next = next;
    }
    private BlockNode find(String id) {

        for (BlockNode current = left; !(current.data instanceof EndBlock); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        for (BlockNode current = right; !(current.data instanceof EndBlock); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        return null;
    }

    void setRight(BlockNode data) {
        BlockNode next = null;
        if(right.data instanceof EndBlock){
            next = new BlockNode(right);
        }else {
            next = right;
        }
        right = new BlockNode(data.data);
        right.next = next;
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
        BlockNode next = current.next;
        current.next = new BlockNode(newBlock);
        current.next.next = next;
        return true;
    }
}