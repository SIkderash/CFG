/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.util.ArrayList;

/**
 *
 * @author sikde
 */
public class MakeGraph {
        ArrayList<String> Statements = new ArrayList<>();
        ArrayList<Node>Graph = new ArrayList<>();
        int iterator = 0;
        
        AllMethods Al = new AllMethods();

        public MakeGraph(ArrayList<String> Lines){
            while(iterator<Lines.size()){
                iterator++;
                String currentLine = Lines.get(iterator);
                //System.out.println(currentLine+"\n");
                if(currentLine)
                
            }
        }
        
}
