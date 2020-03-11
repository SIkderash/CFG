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

    ArrayList<String> Graph = new ArrayList<>();
    ArrayList<String> Lines = new ArrayList<>();
    
    int iteratorOfLines = 0, iterator_2 = 0;
    SyntaxChecker CheckSyntax = new SyntaxChecker();
    Stack stackOfParentNodes = new Stack(); 

    public MakeGraph(ArrayList<String> LinesOfTheSourceCode) {
        for (int i = 0; i < LinesOfTheSourceCode.size(); i++) {
            this.Lines.add(LinesOfTheSourceCode.get(i));
        }
    }

    public void makeRelations()
    {
         int statementNo = 0;
         String currentLine = Lines.get(iteratorOfLines);
          
         Node root = new Node(statementNo,currentLine);
         stackOfParentNodes.add(root);
         Node currentNode = root;
          
        while (!stackOfParentNodes.empty()) {
           
            currentLine = Lines.get(iteratorOfLines);
            if (CheckSyntax.isStatement(currentLine)) {
               
               
                Node node = new Node(statementNo++, currentLine);
                Node parent = currentNode;
                parent.childs.add(node);
                node.parents.add(parent);
                currentNode = node;
            }
            else if(CheckSyntax.foundEnd(currentLine)){
                stackOfParentNodes.pop();
            }
            //System.out.println(currentLine+"\n");
            else if (CheckSyntax.isIf(currentLine)) {
                stackOfParentNodes.add(currentNode);
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
    

}
