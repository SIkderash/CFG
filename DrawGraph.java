package cfg;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//sample class
public class DrawGraph extends Application {
    
    int[] level = new int[100];
    int maxLevel;
    int source;
    int[][] adj = new int[100][100];
    int numberOfNodes;
    ArrayList<ArrayList<Integer> > nodesInLevel = new ArrayList<>();
    
    
    public void bfs(int source){
        this.source = source;
        boolean[] vis = new boolean[100];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        vis[source] = true;
        level[source] = 0;
        while(!q.isEmpty()){
            int cur = q.peek();
            /*
            System.out.println("QueueTop "+ cur);
            //System.out.println(numberOfNodes);
            System.out.print("ADJ of " + cur + "    ");
            */
            for(int i=0; i<numberOfNodes; i++){
                if(adj[cur][i]==1 && vis[i] == false){
                    //System.out.print(i +" ");
                    vis[i] = true;
                    q.add(i);
                    level[i] = level[cur] + 1;
                    maxLevel = max(maxLevel, level[i]);
                    //System.out.println("Node " + i +" LEVEL " + level[i]);
                }
            }
            q.poll();
        }
    }
    void printLeveledGraph(){
        
        System.out.println(maxLevel);
        for(int i=0; i<=maxLevel; i++){
            ArrayList<Integer> thisLevel = new ArrayList<>();
            //System.out.println("LEVEL "+i);
             for(int j=0; j<numberOfNodes; j++){
                 if(j!=source && level[j]==0) continue;
                 if(level[j]==i){
                     thisLevel.add(j);
                 }
             }
             
             if(!thisLevel.isEmpty()) {
                 nodesInLevel.add(thisLevel);
             }
             
        
        }
        for(int i=0; i<nodesInLevel.size(); i++){
            System.out.println("Level "+i);
             for(int j=0; j<nodesInLevel.get(i).size(); j++){
                 System.out.println(nodesInLevel.get(i).get(j)+" ");
            }
        }
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage)  {
    //create a stackpane
        Pane root = new Pane();
        int y = 10;
        for(int i=0; i<nodesInLevel.size(); i++){
            int x = 100;
            for(int j=0; j<nodesInLevel.get(i).size(); j++){
                Button btn = new Button();
                btn.setText(Integer.toString(j));
                btn.setLayoutX(x);
                btn.setLayoutY(y);
                btn.setMaxSize(50, 50);
                root.getChildren().add(btn);
                x+=50;
            } 
            y+=10;
        }
       
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }
    //main method
    public void drawGraphWithAjacencyMatrix(int[][] inputAdj, int numberOfNodes, int source) {
        this.numberOfNodes = numberOfNodes;
        for(int i=0; i<numberOfNodes; i++){
            for(int j=0; j<numberOfNodes; j++){
                this.adj[i][j] = inputAdj[i][j];
            }
        }
        bfs(source);
        printLeveledGraph();
        launch();
    }
}

// for nicely plotting use leveled Grpah and for back edges, use adjacency matrix
