/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author sikde
 */
public class MakeGraph {
    
    ArrayList<String>Lines;
    boolean vis[];
    int[][] adj = new int[50][50];
    SyntaxIdentifier checker = new SyntaxIdentifier();
    int cur = 0;
    //Constructor to pass the lines of code
    public MakeGraph (ArrayList<String> lines){
        this.Lines = lines;
        vis = new boolean[Lines.size()];
        //for(int i=0; i<Lines.size(); i++) System.out.println(i+ " " + Lines.get(i));
    }
    
    public void start() throws IOException{
        cur=0;
        
        while(Lines.get(cur).contains("intmain(){") || Lines.get(cur).charAt(0)=='#' || (Lines.get(cur).charAt(0)=='/' && Lines.get(cur).charAt(0)=='/')){
            cur++;
        }
        
        Node root = new Node(cur,Lines.get(cur));
        cur++;
        
        System.out.println("\n\n\nroot node no: " + root.nodeNumber +"\n"+ "root node statement: "+root.Statement);
        
        makeRelations(root, false);
        
        dfs(root, -1);
        bfs(root);
        printGraph();
        saveGraph();
        //DrawGraph draw = new DrawGraph();
        //nodesInLevel = draw.drawGraphWithAjacencyMatrix(adj,cur+1,root.nodeNumber);
        //System.out.println("nodesInLevel has currently size "+ nodesInLevel.size());
        Draw drawGraph = new Draw();
        drawGraph.main();
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
    
    public void bfs(Node root) throws IOException{
        int[] level = new int[50];
        for(int i=0; i<50; i++) level[i] = 100000000;
        
        level[root.nodeNumber] = 1;
        Queue<Integer>q = new LinkedList<>();
        q.add(root.nodeNumber);
        while(!q.isEmpty()){
            int cur = q.peek();
            q.poll();
            for(int i=0; i<50; i++){
                if(adj[cur][i]==1 && level[i]>level[cur]+1){
                    level[i] = level[cur]+1;
                    //System.out.println(i + " " + level[i]);
                    q.add(i);
                }
            }
        }
        try (FileWriter myWriter = new FileWriter("F:\\Downloads\\CFG-master\\LeveledNodes.txt")) {
            myWriter.write((Lines.size())+"\n");
            for(int i=0; i<Lines.size(); i++){
                myWriter.write(i + " " + level[i]+"\n");
            }
        }
        
    }
    public void printGraph (){
        System.out.println("\nAdjacency List:");
        for(int i=0; i<Lines.size(); i++){
            System.out.print("\t"+i+"  ->   ");
            for(int j=0; j<Lines.size(); j++){
                if(adj[i][j]==1){
                    System.out.print(j+" ");
                }
            }
            System.out.println();
        }
        System.out.println("\nAdjacency Matrix:");
        for(int i=0; i<Lines.size(); i++){
            System.out.print("\t"+i+"\t");
            for(int j=0; j<Lines.size(); j++){
                System.out.print(adj[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void saveGraph() throws IOException{
        try (FileWriter myWriter = new FileWriter("F:\\Downloads\\CFG-master\\Edges.txt")) {
            //myWriter.write((Lines.size())+"\n");
            for(int i=0; i<Lines.size(); i++){
                for(int j=0; j<Lines.size(); j++){
                    if(adj[i][j]==1){
                        myWriter.write(i+" "+j+"\n");
                    }
                }
            }
        }
    }
    
}
