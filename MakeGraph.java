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
public class makeGraph {

    ArrayList<String> Graph = new ArrayList<>();
    ArrayList<String> Lines = new ArrayList<>();
    int statementNo = 1;
    Node parentNode = null;
    Node parentOfBranch = null;
    int iterator_1 = 0, iterator_2 = 0;
    AllMethods Al = new AllMethods();
    Stack<Integer> stack = new Stack<Integer>();

    public makeGraph(ArrayList<String> Lins) {
        for (int i = 0; i < Lins.size(); i++) {
            this.Lines.add(Lins.get(i));
        }
    }

    public void makeRelations()
    {
        while (iterator_1 < Lines.size()) {

            String currentLine = Lines.get(iterator_1);
            iterator_1++;
            if (Al.isStatement(currentLine)) {
                Node node = new Node(statementNo, currentLine);
                node.parents.add(parentNode);
                parentNode.childs.add(node);
                parentNode = node;
                Graph.add(currentLine);
                statementNo++;
            }
            //System.out.println(currentLine+"\n");
            if (Al.isIf(currentLine)) {
                parentOfBranch = parentNode;
                stack.push(parentOfBranch.nodeNumber);
                while (!stack.empty()) {
                    currentLine = Lines.get(iterator_1);
                    iterator_1++;
                    if(Al.foundEnd(currentLine)){
                        stack.pop();
                        continue;
                    }
                    else if (Al.isStatement(currentLine)) {
                        Node node = new Node(statementNo, currentLine);
                        parentNode = new Node(stack.peek(),Graph.get(stack.peek()));
                        node.parents.add(parentNode);
                        parentNode.childs.add(node);
                        parentNode = node;
                        Graph.add(currentLine);
                        statementNo++;
                    }
                    else if(Al.isIf(currentLine))
                }

            }

        }
        for (int i = 0; i < Graph.size(); i++) {
            System.out.print(Graph.get(i)+ "\t");
        }

    }

}
