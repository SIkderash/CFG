/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
    ArrayList<ArrayList<Node> >graph = new ArrayList<ArrayList<Node>>();
    Queue<Node> q = new LinkedList<>();
    

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
         Node branchParentStored = root;
         ArrayList<Node>branchLeaves = new ArrayList<>();
         boolean inBranch = false;
        while (!stackOfParentNodes.empty()) {
            
            currentLine = Lines.get(iteratorOfLines);
            if (CheckSyntax.isStatement(currentLine)) {
               
                Node node = new Node(++statementNo, currentLine);
                Node parent = currentNode;
                parent.childs.add(node);
                node.parents.add(parent);
                currentNode = node;
                if(!inBranch && !branchLeaves.isEmpty() ){
                    for(int i=0; i<branchLeaves.size(); i++){
                        node.parents.add(branchLeaves.get(i));
                    }
                }
                System.out.println(currentNode.nodeNumber + "->  " + currentNode.Statement);
            }
            else if(CheckSyntax.foundEnd(currentLine)){
                branchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("BranchParentStored " + branchParentStored.nodeNumber + "->  " + branchParentStored.Statement);
                stackOfParentNodes.pop();
                System.out.println(currentLine);
                if(inBranch==true){
                    branchLeaves.add(currentNode);
                }
            }
            //System.out.println(currentLine+"\n");
            else if (CheckSyntax.isIf(currentLine)) {
                inBranch = true;
                stackOfParentNodes.add(currentNode);
                branchParentStored = (Node) stackOfParentNodes.peek();
                System.out.println("if->  " + currentLine);
            }
            else if(CheckSyntax.isElseIf(currentLine)){
                inBranch = true;
                stackOfParentNodes.add(branchParentStored);
                currentNode = branchParentStored;
                System.out.println("else if->  " + currentLine);
               
            }
            else if(CheckSyntax.isElse(currentLine)){
                inBranch = true;
                stackOfParentNodes.add(branchParentStored);
                currentNode = branchParentStored;
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
        //System.out.print(root.Statement + "\n");
        if(root!=null){q.add(root);}
        print(root);
        
    }
    public void print(Node traversingNode){
       //System.out.println("y");
       Node imaginary_root = new Node(-1,"");
       imaginary_root.childs.add(root);                                                                                                      ;
       ArrayList<Node> temp1 = new ArrayList<>();
       temp1.add(imaginary_root);
       graph.add(temp1);
       while(!q.isEmpty()){
           ArrayList<Node> temp = new ArrayList<>();
         for(int i=0; i<q.peek().childs.size(); i++){
             temp.add(q.peek().childs.get(i));
             q.add(q.peek().childs.get(i));
         }
         Node remNode = q.poll();
         graph.add(temp);
    }
       for(int i=0; i<graph.size(); i++){
           System.out.print(i + "\t");
           for(int j=1; j<graph.get(i).size(); j++){
              System.out.print(graph.get(i).get(j).nodeNumber + " ");
           }
           System.out.println();
       }
       DrawGraph DG = new DrawGraph(graph);
       DG.draw();
       
    }

}
