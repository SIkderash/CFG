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
        p1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int tempx = 500, tempy = 100, width = 100, height = 20;
        gbc.gridx = 500;
        gbc.gridy = 50; 
        Queue<Node>q = new LinkedList<>();
        //System.out.print(q.peek().nodeNumber);
        q.add(graph.get(0).get(0));
        
        System.out.print(q.peek().nodeNumber + "*");
        while(!q.isEmpty()){
            for(int i=0; i<graph.get(q.peek().nodeNumber).size(); i++)
            {
                JButton node = new JButton(Integer.toString(q.peek().nodeNumber));
                p1.add(node);
            }
            
        }
        
        f1.add(p1);
        
        f1.setSize(2000,900);
        f1.setVisible(true);
    }
}