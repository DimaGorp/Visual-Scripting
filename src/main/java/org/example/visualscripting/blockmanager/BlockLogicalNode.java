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

    private BlockNode union;

    public BlockLogicalNode() {
        left = null;
        right = null;
        union = null;
        leftId = UUID.randomUUID().toString();
        rightId = UUID.randomUUID().toString();
        next = null;

    }

    public BlockLogicalNode(Block data, BlockNode end) {
        this();
        left = end;
        right = end;
        union = end;;
        this.data = data;
        next = union;
    }

    public String getLeftId(){
        return leftId;
    }
    public String getRightId(){
        return rightId;
    }
    public BlockNode getUnion() { return union; }
    public void setUnion(BlockNode union) { this.union = union; }
    BlockNode getLeft() {
        return left;
    }
    BlockNode getRight() {
        return right;
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
        if (!(next.data instanceof EndBlock)) {
            connectToUnion(left);
        }
    }
    private void connectToUnion(BlockNode branch) {
        BlockNode current = branch;
        while (current.next != null && !(current.next.data instanceof EndBlock)) {
            current = current.next;
        }
        current.next = union;
    }
    private BlockNode find(String id) {

        for (BlockNode current = left; (current != union); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        for (BlockNode current = right; (current != union) ; current = current.next) {
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


    public boolean insert(Block<?> newBlock, String Id) throws NullPointerException {
        BlockNode current = find(Id);
        if(current == null) {
            BlockNode next = union;
            union = new BlockNode(newBlock);
            union.next = next;
            return true;
        }
        BlockNode next = current.next;
        current.next = new BlockNode(newBlock);
        current.next.next = next;
        if (data instanceof IfValueBlock) {
            connectToUnion(current.next);
        }
        return true;
    }
}