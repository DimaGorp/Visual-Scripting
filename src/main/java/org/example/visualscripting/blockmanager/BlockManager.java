
package org.example.visualscripting.blockmanager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.visualscripting.blocks.Block;
import org.example.visualscripting.blocks.EndBlock;
import org.example.visualscripting.blocks.StartBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;


public class BlockManager {

    private class BlockNode{
        protected Block data;
        protected BlockNode next;
        public BlockNode(){
            data = null;
            next = null;
        }
        public BlockNode(Block data){
            this();
            this.data = data;
        }
    };
    private class BlockLogicalNode extends BlockNode{
        private BlockNode left;
        private BlockNode right;
        public BlockLogicalNode(){
            left = null;
            right = null;
        }
        public BlockLogicalNode(Block data){
            this();
            this.data = data;
        }
        void setLeft(Block data){
            left = new BlockNode(data);
        }
        void setRight(Block data){
            right = new BlockNode(data);
        }
        BlockNode getLeft(){
            return left;
        }
        BlockNode getRight(){
            return right;
        }
    };
    private BlockNode head;
    private BlockNode tail;
    BlockManager(){
        head = new BlockNode(new StartBlock());
        tail = new BlockNode(new EndBlock());
    }

    boolean Add(Block newBlock){
        for(BlockNode cur = head; cur!=tail;cur = cur.next){
            if(cur.next!=tail){
                if(newBlock instanceof org.example.visualscripting.blocks.IfValueBlock){
                    cur.next = new BlockLogicalNode(newBlock);
                    BlockLogicalNode curLogical = (BlockLogicalNode) cur.next;
                    curLogical.setLeft(tail.data);
                    curLogical.setRight(tail.data);
                    return true;
                }   
                cur.next = new BlockNode(newBlock);
                return true;
            }
        }
        return false;
    }
    void Print(){
        for(BlockNode cur = head; cur!=tail;cur = cur.next){
            if(cur.data instanceof org.example.visualscripting.blocks.IfValueBlock){
                System.out.println(cur.data.toString());
                for(BlockNode curLeft = ((BlockLogicalNode)cur).getLeft(); curLeft!=tail;cur = curLeft.next){
                    System.out.println(curLeft.data.toString());
                }
                for(BlockNode curRight = ((BlockLogicalNode)cur).getRight(); curRight!=tail;curRight = cur.next){
                    System.out.println(curRight.data.toString());
                }
            } 
            System.out.println(cur.data.toString());  
        }
    }
    
}
