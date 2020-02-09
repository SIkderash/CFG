/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.beans.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sikde
 */
public class SourceCodeReader {

    public SourceCodeReader(String s) throws FileNotFoundException{
      
        File file = new File(s);
        Scanner scan = new Scanner(file);
        ArrayList<String> Lines = new ArrayList<>();
        ArrayList<String> Statements = new ArrayList<>();
        ArrayList<Node>Graph = new ArrayList<>();
        AllMethods Al = new AllMethods();
        
        int statementNo = 1;
        Node parentNode= null;
        Node ParentOfBranch = null;
            while (scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                Lines.add(currentLine);
                if(Al.isStatement(currentLine)){
                    Statements.add(currentLine);
                    Node node = new Node(statementNo, currentLine);
                    node.parents.add(parentNode);
                    parentNode.childs.add(node);
                    parentNode = node;
                    Graph.add(node);
                    statementNo++;
                }
                else if(true){
                    if(Al.isIf(currentLine)){
                        ParentOfBranch = parentNode;
                        while(scan.hasNextLine()){
                            
                        }
                    }
                }
            }
           
            

            /*for (int i = 0; i < Statements.size(); i++) {
               System.out.println(Statements.get(i));
            }*/
            for (int i = 0; i < Graph.size(); i++) {
                System.out.print("Node: "+ Graph.get(i).nodeNumber+"\t");
                System.out.println(Graph.get(i).Statement+ "\n");
            }
            
            
     
        
    }
}
    
