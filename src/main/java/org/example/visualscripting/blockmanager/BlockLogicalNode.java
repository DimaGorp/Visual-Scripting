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
    public void setUnion(BlockNode union) { 
        if(!left.data.equals(this.union.data)){
            for(BlockNode cur = left;!cur.data.equals(this.union.data);cur = cur.next){
                if(cur.next.data.equals(this.union.data)){
                    cur.next = union;
                    break;
                }
            }
        }
        if(!right.data.equals(this.union.data)){
            for(BlockNode cur = right;!cur.data.equals(this.union.data);cur = cur.next){
                if(cur.next.data.equals(this.union.data)){
                    cur.next = union;
                    break;
                }
            }
        }
        this.union = union; 
    }
    BlockNode getLeft() {
        return left;
    }
    BlockNode getRight() {
        return right;
    }

    void setLeft(BlockNode data) {
        BlockNode next = null;
        if(left == union){
            next = new BlockNode(left);
        }else {
            next = left;
        }
        left = new BlockNode(data.data);
        left.next = next;
    }
    private BlockNode find(String id) {

        for (BlockNode current = left; (!current.data.equals(union.data)); current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        for (BlockNode current = right; (!current.data.equals(union.data)) ; current = current.next) {
            if(current.data.getId().equals(id)) {
                return current;
            }
        }
        return null;
    }

    void setRight(BlockNode data) {
        BlockNode next = null;
        if(right == union){
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
            return false;
        }
        if (newBlock instanceof IfValueBlock) {
            BlockLogicalNode logicNode = new BlockLogicalNode(newBlock, current.next);
            logicNode.insert(current.next.data, Id);
            current.next = logicNode;
            return true;
        }
        BlockNode next = current.next;
        current.next = new BlockNode(newBlock);
        current.next.next = next;
        return true;
    }
    public BlockNode print(){
        System.out.println(data.getId() + " " + data.getName());
        System.out.println("\t"+getLeftId() + " - true");
        BlockNode curLeft = getLeft();
        BlockNode curRight = getRight();
        for(; !(curLeft.data.equals(union.data) );curLeft = curLeft.next){
            if(curLeft.data instanceof IfValueBlock){
                BlockLogicalNode ifBlock = (BlockLogicalNode)curLeft;
                curLeft = ifBlock.print();
                continue;
            }
            System.out.println("\t\t"+curLeft.data.getId() + " " +curLeft.data.getName());
        }
        System.out.println("\t"+getRightId() + " - false");
        for(; !(curRight.data.equals(union.data));curRight = curRight.next){
            if(curRight.data instanceof IfValueBlock){
                BlockLogicalNode ifBlock = (BlockLogicalNode)curRight;
                curRight = ifBlock.print();
                continue;
            }
            System.out.println("\t\t"+curRight.data.getId() + " " +curRight.data.getName());
        }
        System.out.println(union.data.getId() + " " + union.data.getName());
        return union;
    }
}