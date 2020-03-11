/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.util.ArrayList;

/**
 *
 * @author shanto
 */
public class Stack {
    ArrayList<Node>stack;
    int top= -1;
    
    public void push(Node node){
        stack.add(node);
        top++;
    }
    
    public void pop(){
        top--;
    }
    public Node peek(){
        return stack.get(top);
    }
    public boolean isEmpty(){
        if(top<0){
            return true;
        }
        return false;
    }
    
}
