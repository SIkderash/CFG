/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

/**
 *
 * @author Asus
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DrawGraph {
     public ArrayList<ArrayList<Node>>graph = new  ArrayList<>(); 
     
     
    public DrawGraph(ArrayList<ArrayList<Node>> graph){
          this.graph.addAll(graph);
    }
    
    
    public void draw(){
        
        JFrame f1 = new JFrame("Control Flow Graph");
        f1.setBackground(Color.BLUE);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p1 = new JPanel();
        
        int constx = 0, consty = 10, width = 10, height = 5;
        
        Queue<Node>q = new LinkedList<>();
        for(int i=0; i<graph.size(); i++){
            System.out.print(i+"-> ");
             for(int j=0; j<graph.get(i).size(); j++)
             {
                 System.out.print(graph.get(i).get(j).nodeNumber+" ");
             }
             
             System.out.print("\n");
         }
        
        q.add(graph.get(1).get(0));
              
        System.out.print(graph.get(1).get(0).nodeNumber);
        JButton b = new JButton(Integer.toString(graph.get(1).get(0).nodeNumber));
                b.setBackground(Color.yellow);
                b.setBounds(constx, consty, (int)width, height);
                p1.add(b);
        int x = constx, y = consty;
        while(!q.isEmpty()){
            int i;
            int node_num = q.peek().nodeNumber;
            System.out.print(q.peek().nodeNumber+"\n");
            
            for(i=0; i<graph.get(node_num+2).size(); i++){
                JButton button = new JButton(Integer.toString(graph.get(node_num+2).get(i).nodeNumber));
                button.setBackground(Color.yellow);
                button.setBounds(x, y, (int) width, height);
                p1.add(button);
                q.add(graph.get(node_num+2).get(i));
                if(i==graph.get(node_num+2).size()/2){
                    i++;
                    break;
                }
                x-=10;
            }
            for(i=i; i<graph.get(node_num+2).size(); i++){
                JButton button = new JButton(Integer.toString(graph.get(node_num+2).get(i).nodeNumber));
                button.setBackground(Color.yellow);
                button.setBounds(x, y, (int) width, height);
                p1.add(button);
                q.add(graph.get(node_num+2).get(i));
                x+=10;
            }
            q.poll();
            y+=120;
        }
        
        f1.add(p1);
        f1.setSize(2000,900);
        f1.setVisible(true);
    }
}
