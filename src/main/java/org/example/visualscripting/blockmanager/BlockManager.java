
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
                    BlockLogicalNode logicNode = new BlockLogicalNode(newBlock, tail);
                    cur.next = logicNode;
                    return true;
                }
                BlockNode next = cur.next;
                cur.next = new BlockNode(newBlock);
                cur.next.next = next;
                return true;
            }else{
                if (cur instanceof BlockLogicalNode) {
                    BlockLogicalNode logicalNode = (BlockLogicalNode)cur;
                    if (logicalNode.getLeftId().equals(Id)) {
                        logicalNode.setLeft(new BlockNode(newBlock));
                        return true;
                    } else if (logicalNode.getRightId().equals(Id)){
                        logicalNode.setRight(new BlockNode(newBlock));
                        return true;
                    } else {
                        return logicalNode.insert(newBlock, Id);
                    }
                }
            }

        }
        return false; // Если блока с таким ID нет
    }

    public void print(){
        for(BlockNode cur = head; cur!=tail;cur = cur.next){

            System.out.println(cur.data.getId() + " " + cur.data.getName());

            if(cur.data instanceof IfValueBlock){
                BlockLogicalNode ifBlock = (BlockLogicalNode)cur;
                System.out.println(ifBlock.getLeftId() + " - true");

                for(BlockNode curLeft = ((BlockLogicalNode)cur).getLeft(); !(curLeft.data instanceof EndBlock);curLeft = curLeft.next){
                    System.out.println(curLeft.data.getId() + " " +curLeft.data.getName());
                }
                System.out.println(ifBlock.getRightId() + " - false");
                for(BlockNode curRight = ((BlockLogicalNode)cur).getRight(); !(curRight.data instanceof EndBlock);curRight = curRight.next){
                    System.out.println(curRight.data.getId() + " " +curRight.data.getName());
                }
                break; 
            } 
            
        }
        System.out.println(tail.data.getId() + " " +tail.data.getName());
    }
    
}
