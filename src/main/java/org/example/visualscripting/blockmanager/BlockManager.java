
package org.example.visualscripting.blockmanager;

import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blocks.EndBlock;
import org.example.visualscripting.blocks.IfValueBlock;
import org.example.visualscripting.blocks.StartBlock;

import java.util.ArrayList;
import java.util.List;


public class BlockManager {

    private BlockNode head;
    private BlockNode tail;

    public BlockManager(){
        head = new BlockNode(new StartBlock());
        tail = new BlockNode(new EndBlock());
        head.next = tail;
    }
    public List<Block> getBlock(){
        List<Block> blocks = new ArrayList<Block>();
        for (BlockNode cur = head; cur != tail; cur = cur.next){
            blocks.add(cur.data);
        }
        blocks.add(tail.data);
        return blocks;
    }
    public boolean insert(Block<?> newBlock, String Id) {
        for (BlockNode cur = head; cur != tail; cur = cur.next) {
            if (cur instanceof BlockLogicalNode) {
                BlockLogicalNode logicalNode = (BlockLogicalNode)cur;
                if(logicalNode.data.getId().equals(Id)){
                    BlockNode next = logicalNode.getUnion();
                    BlockNode union = new BlockNode(newBlock);
                    union.next = next;
                    if(logicalNode.getLeft().data.equals(logicalNode.getUnion().data) && logicalNode.getRight().data.equals(logicalNode.getUnion().data)){
                        logicalNode.setLeft(union);
                        logicalNode.setRight(union);
                    }
                    logicalNode.setUnion(union);
                    
                    return true;
                }
                if (logicalNode.getLeftId().equals(Id)) {
                    logicalNode.setLeft(new BlockNode(newBlock));
                    return true;
                } else if (logicalNode.getRightId().equals(Id)){
                    logicalNode.setRight(new BlockNode(newBlock));
                    return true;
                } else {
                    if(logicalNode.insert(newBlock, Id)){
                        return true;
                    }else{
                        cur = logicalNode.getUnion();
                        if (cur.data.getId().equals(Id)) {  // Проверяем ID
                            if (newBlock instanceof IfValueBlock) {
                                BlockLogicalNode logicNode = new BlockLogicalNode(newBlock, cur.next);
                                logicNode.insert(cur.next.data, Id);
                                cur.next = logicNode;
                                return true;
                            }
                            BlockNode next = cur.next;
                            cur.next = new BlockNode(newBlock);
                            cur.next.next = next;
                            return true;
                        }
                        continue;
                    }
                }
                //if(logicalNode.data.getId().equals(Id))
            }else {

                if (cur.data.getId().equals(Id)) {  // Проверяем ID
                    if (newBlock instanceof IfValueBlock) {
                        BlockLogicalNode logicNode = new BlockLogicalNode(newBlock, cur.next);
                        logicNode.insert(cur.next.data, Id);
                        cur.next = logicNode;
                        return true;
                    }
                    BlockNode next = cur.next;
                    cur.next = new BlockNode(newBlock);
                    cur.next.next = next;
                    return true;
                }

            }
        }
        return false;
    }

    public void print(){
        for(BlockNode cur = head; cur!=tail;cur = cur.next){

            System.out.println(cur.data.getId() + " " + cur.data.getName());

            if(cur.data instanceof IfValueBlock){
                BlockLogicalNode ifBlock = (BlockLogicalNode)cur;
                cur = ifBlock.print();
                continue; 
            } 
            
        }
        System.out.println(tail.data.getId() + " " +tail.data.getName());
    }
    
}
