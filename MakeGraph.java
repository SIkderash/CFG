/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author sikde
 */
public class MakeGraph {
    
    ArrayList<String>Lines;
    ArrayList<ArrayList<Integer> > nodesInLevel = new ArrayList<>();
    boolean vis[];
    int[][] adj = new int[50][50];
    SyntaxChecker checker = new SyntaxChecker();
    int cur = 0;
    //Constructor to pass the lines of code
    public MakeGraph (ArrayList<String> lines){
        this.Lines = lines;
        vis = new boolean[Lines.size()];
        for(int i=0; i<Lines.size(); i++) System.out.println(i+ " " + Lines.get(i));
    }
    
    public void start() throws IOException{
        cur=0;
        
        while(Lines.get(cur).contains("int main(){") || Lines.get(cur).charAt(0)=='#'){
            cur++;
        }
        
        Node root = new Node(cur,Lines.get(cur));
        cur++;
        
        System.out.println("root " + root.nodeNumber +" "+ root.Statement);
        
        makeRelations(root, false);
        
        dfs(root, -1);
        DrawGraph draw = new DrawGraph();
        nodesInLevel = draw.drawGraphWithAjacencyMatrix(adj,cur+1,root.nodeNumber);
        //System.out.println("nodesInLevel has currently size "+ nodesInLevel.size());
    }
    
    
    public Node makeRelations(Node branchRoot, boolean inLoop){
        Node par = branchRoot;
        //System.out.println(cur);
        ArrayList<Node> branchingsOfThisBranch = new ArrayList<>();
        //System.out.println(branchRoot.nodeNumber+" "+inLoop);
        
        while(cur<Lines.size()) {
        //System.out.println("finished " + cur);
        Node curNode = new Node(cur,Lines.get(cur));
        
        if(checker.isElse(curNode.Statement)){
            //System.out.println("Else - "+ curNode.Statement);
            
            par.childs.add(curNode);
            cur++;
            branchingsOfThisBranch.add(makeRelations(curNode, false));
        }
        
        
        
        
        
        
        
        else if(checker.isElseIf(curNode.Statement)){
            //System.out.println("Else If - "+ curNode.Statement);
            
            par.childs.add(curNode);
            cur++;
            branchingsOfThisBranch.add(makeRelations(curNode, false));
        }
        
        
        
        
        
        
        else if(checker.isIf(curNode.Statement)){
            //System.out.println("If - "+ curNode.Statement);
            
            if(branchingsOfThisBranch.size()>0){
                for(int i=0; i<branchingsOfThisBranch.size(); i++){
                    branchingsOfThisBranch.get(i).childs.add(curNode);
                    branchingsOfThisBranch.clear();
                }
            }
            else{
                par.childs.add(curNode);
            }
            cur++;
            branchingsOfThisBranch.add(makeRelations(curNode, false));
        }
        
        
        
        
        
        
        else if(checker.isLoop(curNode.Statement)){
            //System.out.println("loop - "+ curNode.Statement);
            
            if(branchingsOfThisBranch.size()>0){
                for(int i=0; i<branchingsOfThisBranch.size(); i++){
                    branchingsOfThisBranch.get(i).childs.add(curNode);
                    branchingsOfThisBranch.clear();
                }
            }
            else{
                par.childs.add(curNode);
            }
            branchingsOfThisBranch.add(curNode);
            cur++;
            makeRelations(curNode, true);
        }
        
        
        
        
        
        
        else{
            //System.out.println("Statement - "+ curNode.Statement+branchingsOfThisBranch.size());
             if(branchingsOfThisBranch.size()>0){
                for(int i=0; i<branchingsOfThisBranch.size(); i++){
                    branchingsOfThisBranch.get(i).childs.add(curNode);
                }
                branchingsOfThisBranch.clear();
            }
            else{
                par.childs.add(curNode);
            }
            //branchingsOfThisBranch.add(curNode);
            cur++;
            if(checker.foundEnd(curNode.Statement)){
                if(inLoop==true) {
                    System.out.println(curNode.nodeNumber);
                    System.out.println(branchRoot);
                    curNode.childs.add(branchRoot);
                }
                return curNode;
            }
            par = curNode;
        }
           
        
        
        
            
        
        }
        return null;
    }
    
    
    
    public void dfs(Node cur, int prev){
        //System.out.println(prev + " " + cur.nodeNumber+" "+cur.Statement);
        vis[cur.nodeNumber] = true;
        
        for(int i=0; i<cur.childs.size(); i++){
            int nodeNo = cur.childs.get(i).nodeNumber;
            if(vis[nodeNo]==false){
                dfs(cur.childs.get(i), cur.nodeNumber);
            }
        }
        
        for(int i=0; i<cur.childs.size(); i++){
            adj[cur.nodeNumber][cur.childs.get(i).nodeNumber] = 1;
        }
    }
    
    public void printGraph (){
        
        for(int i=0; i<Lines.size(); i++){
            System.out.print(i+"  ->   ");
            for(int j=0; j<Lines.size(); j++){
                if(adj[i][j]==1){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
    }
    
}
