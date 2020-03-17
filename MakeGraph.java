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
    
    ArrayList<String> Lines = new ArrayList<>();   //Canprint all nodes
    
    int iteratorOfLines = 0;
    int statementNo ;
    SyntaxChecker CheckSyntax = new SyntaxChecker();
    Stack stackOfParentNodes = new Stack();
    Node root;
    ArrayList<Node>graph = new ArrayList<>();
    

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
          statementNo =0;
         String currentLine = Lines.get(iteratorOfLines);
         while(!CheckSyntax.isStatement(currentLine) && iteratorOfLines<Lines.size()){
            
             currentLine = Lines.get(iteratorOfLines);
              iteratorOfLines++;
     
         }
          
         root = new Node(statementNo,currentLine);
         stackOfParentNodes.add(root);
         Node currentNode = root;
         System.out.println(currentNode.nodeNumber + "->  " + currentNode.Statement);
         Node BranchParentStored = root;
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
                BranchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("BranchParentStored " + BranchParentStored.nodeNumber + "->  " + BranchParentStored.Statement);
                stackOfParentNodes.pop();
                System.out.println(currentLine);
            }
            //System.out.println(currentLine+"\n");
            else if (CheckSyntax.isIf(currentLine)) {
                stackOfParentNodes.add(currentNode);
                BranchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("if->  " + currentLine);
            }
            else if(CheckSyntax.isElseIf(currentLine)){
                stackOfParentNodes.add(BranchParentStored);
                currentNode = BranchParentStored;
                System.out.println("else if->  " + currentLine);
               
            }
            else if(CheckSyntax.isElse(currentLine)){
                stackOfParentNodes.add(BranchParentStored);
                currentNode = BranchParentStored;
                System.out.println("else->  " + currentLine);
               
            }
            
            else if(CheckSyntax.isFor(currentLine)){
                stackOfParentNodes.add(currentNode);
                System.out.println("for->  " + currentLine);
            }
            else if(CheckSyntax.isWhile(currentLine)){
                stackOfParentNodes.add(currentNode);
                System.out.println("while->  " + currentLine);
            }
             iteratorOfLines++;

        }
        
        
        

    }
    public void printGraph(){
        print(root);
         int[] graphIndexedWithParents = new int[graph.size()];
        for(int i=1; i<this.graph.size(); i++){
            //graphIndexedWithParents[i] = this.graph.get(i).parents.get(0).nodeNumber;
            //System.out.print(graphIndexedWithParents[i]+" ");
            //System.out.print(this.graph.get(i).parents.get(0).nodeNumber);
        }
        
    }
    public void print(Node traversingNode){
       //System.out.println("y");
      
         if(!traversingNode.childs.isEmpty()){
         for(int i=0; i<traversingNode.childs.size(); i++){
             Node child = traversingNode.childs.get(i);
             System.out.print(child.parents.get(0).nodeNumber);
             this.graph.add(traversingNode);
             print(child);
         }   
        }
    }
    

}
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
    
    ArrayList<String> Lines = new ArrayList<>();   //Canprint all nodes
    
    int iteratorOfLines = 0;
    int statementNo ;
    SyntaxChecker CheckSyntax = new SyntaxChecker();
    Stack stackOfParentNodes = new Stack();
    Node root;
    ArrayList<Node>graph = new ArrayList<>();
    

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
          statementNo =0;
         String currentLine = Lines.get(iteratorOfLines);
         while(!CheckSyntax.isStatement(currentLine) && iteratorOfLines<Lines.size()){
            
             currentLine = Lines.get(iteratorOfLines);
              iteratorOfLines++;
     
         }
          
         root = new Node(statementNo,currentLine);
         stackOfParentNodes.add(root);
         Node currentNode = root;
         System.out.println(currentNode.nodeNumber + "->  " + currentNode.Statement);
         Node BranchParentStored = root;
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
                BranchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("BranchParentStored " + BranchParentStored.nodeNumber + "->  " + BranchParentStored.Statement);
                stackOfParentNodes.pop();
                System.out.println(currentLine);
            }
            //System.out.println(currentLine+"\n");
            else if (CheckSyntax.isIf(currentLine)) {
                stackOfParentNodes.add(currentNode);
                BranchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("if->  " + currentLine);
            }
            else if(CheckSyntax.isElseIf(currentLine)){
                stackOfParentNodes.add(BranchParentStored);
                currentNode = BranchParentStored;
                System.out.println("else if->  " + currentLine);
               
            }
            else if(CheckSyntax.isElse(currentLine)){
                stackOfParentNodes.add(BranchParentStored);
                currentNode = BranchParentStored;
                System.out.println("else->  " + currentLine);
               
            }
            
            else if(CheckSyntax.isFor(currentLine)){
                stackOfParentNodes.add(currentNode);
                System.out.println("for->  " + currentLine);
            }
            else if(CheckSyntax.isWhile(currentLine)){
                stackOfParentNodes.add(currentNode);
                System.out.println("while->  " + currentLine);
            }
             iteratorOfLines++;

        }
        
        
        

    }
    public void printGraph(){
        print(root);
         int[] graphIndexedWithParents = new int[graph.size()];
        for(int i=1; i<this.graph.size(); i++){
            //graphIndexedWithParents[i] = this.graph.get(i).parents.get(0).nodeNumber;
            //System.out.print(graphIndexedWithParents[i]+" ");
            //System.out.print(this.graph.get(i).parents.get(0).nodeNumber);
        }
        
    }
    public void print(Node traversingNode){
       //System.out.println("y");
      
         if(!traversingNode.childs.isEmpty()){
         for(int i=0; i<traversingNode.childs.size(); i++){
             Node child = traversingNode.childs.get(i);
             System.out.print(child.parents.get(0).nodeNumber);
             this.graph.add(traversingNode);
             print(child);
         }   
        }
    }
    

}
