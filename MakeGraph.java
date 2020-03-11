/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author sikde
 */
public class MakeGraph {
    
    ArrayList<String> Lines = new ArrayList<>();
    
    int iteratorOfLines = 0;
    SyntaxChecker CheckSyntax = new SyntaxChecker();
    Stack stackOfParentNodes = new Stack();
    Node root;
    ArrayList<Integer>graph = new ArrayList<>();

    /**
     *
     * @param LinesOfTheSourceCode
     */
    public MakeGraph(ArrayList<String> LinesOfTheSourceCode) {
        for (int i = 0; i < LinesOfTheSourceCode.size(); i++) {
            this.Lines.add(LinesOfTheSourceCode.get(i));
            System.out.println(Lines.get(i));
        }
    }

    public void makeRelations()
    {
         int statementNo = 0;
         String currentLine = Lines.get(iteratorOfLines);
         while(!CheckSyntax.isStatement(currentLine) && iteratorOfLines<Lines.size()){
            
             currentLine = Lines.get(iteratorOfLines);
              iteratorOfLines++;
     
         }
          
         root = new Node(statementNo,currentLine);
         stackOfParentNodes.add(root);
         Node currentNode = root;
         System.out.println(currentNode.nodeNumber + "->  " + currentNode.Statement);
          
        while (!stackOfParentNodes.empty()) {
           
            currentLine = Lines.get(iteratorOfLines);
            if (CheckSyntax.isStatement(currentLine)) {
               
               
                Node node = new Node(++statementNo, currentLine);
                Node parent = currentNode;
                parent.childs.add(node);
                node.parents.add(parent);
                currentNode = node;
                System.out.println(currentNode.nodeNumber + "->  " + currentNode.Statement);
            }
            else if(CheckSyntax.foundEnd(currentLine)){
                stackOfParentNodes.pop();
                System.out.println(currentLine);
            }
            //System.out.println(currentLine+"\n");
            else if (CheckSyntax.isIf(currentLine)) {
                stackOfParentNodes.add(currentNode);
                System.out.println("if->  " + currentLine);
            }
            else if(CheckSyntax.isElseIf(currentLine)){
                continue;
            }
            else if(CheckSyntax.isFor(currentLine)){
                stackOfParentNodes.add(currentNode);
            }
            else if(CheckSyntax.isWhile(currentLine)){
                stackOfParentNodes.add(currentNode);
            }
             iteratorOfLines++;

        }
        
        
        

    }
    public void printGraph(){
        print(root);
        for(int i=0; i<this.graph.size(); i++){
            //System.out.println("x");
            System.out.print(graph.get(i));
            
        }
        
    }
    public void print(Node traversingNode){
       //System.out.println("y");
      
         if(!traversingNode.childs.isEmpty()){
         for(int i=0; i<traversingNode.childs.size(); i++){
             Node child = traversingNode.childs.get(i);
             this.graph.add(traversingNode.nodeNumber);
             print(child);
         }   
        }
    }
    

}
