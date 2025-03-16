
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
            if (cur.data.getId().equals(Id)) {  // Проверяем ID

                if (newBlock instanceof IfValueBlock) {

                    BlockLogicalNode logicNode = new BlockLogicalNode(newBlock);
                    if(logicNode.getLeftId().equals(Id)){
                        logicNode.setLeft(new BlockNode(newBlock));
                    }else if(logicNode.getRightId().equals(Id)){
                        logicNode.setRight(new BlockNode(newBlock));
                    } else{
                        return logicNode.insert(newBlock, Id);
                    }

                } else {
                    BlockNode next = cur.next;
                    cur.next = new BlockLogicalNode(newBlock);
                    cur.next.next = next;
                }
                return true;
            }

        }
        return false; // Если блока с таким ID нет
    }

    public void print(){
        for(BlockNode cur = head; cur!=tail;cur = cur.next){
            System.out.println(cur.data.getId() + " " +cur.data.getName());
            if(cur.data instanceof IfValueBlock){
                for(BlockNode curLeft = ((BlockLogicalNode)cur).getLeft(); curLeft!=tail;cur = curLeft.next){
                    System.out.println(curLeft.data.getId() + " " +curLeft.data.getName());
                }
                for(BlockNode curRight = ((BlockLogicalNode)cur).getRight(); curRight!=tail;curRight = cur.next){
                    System.out.println(curRight.data.getId() + " " +curRight.data.getName());
                }
                break; 
            } 
            
        }
        System.out.println(tail.data.getId() + " " +tail.data.getName());
    }
    
}
