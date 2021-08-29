/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

/**
 *
 * @author Asus
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Scanner;
import static javafx.application.Application.launch;

public class Draw extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        initGraph(root);

        Scene scene = new Scene(root, 1800, 800);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initGraph(Group root) throws FileNotFoundException {
        File file1 = new File("F:\\Downloads\\CFG-master\\LeveledNodes.txt");
        File file2 = new File("F:\\Downloads\\CFG-master\\test.txt");
        Scanner scan1 = new Scanner(file1);
        Scanner scan2 = new Scanner(file2);
        
        
        ArrayList<String> Lines = new ArrayList<>();
        int numberOfNodes = 0;
        HashMap<Integer, GraphicNode> map = new HashMap<>();
        int[] numberofNodesInLevel = new int[50];
        
        
        while (scan2.hasNextLine()) {
                String currentLine = scan2.nextLine();
                //currentLine = currentLine.replaceAll("\\s","");
                Lines.add(currentLine);   
        }
        
        
        numberOfNodes = scan1.nextInt();
        //System.out.println(numberOfNodes);
        
        while(scan1.hasNextInt()){
            int nodeNumber = scan1.nextInt();
            int levelOfCurNode = scan1.nextInt();
            if(levelOfCurNode>=100000000){
                continue;
            }
            int thisLevelAlreadyHasNodes = numberofNodesInLevel[levelOfCurNode];
            numberofNodesInLevel[levelOfCurNode]++;
            int adjustor = thisLevelAlreadyHasNodes;
            GraphicNode curNode = new GraphicNode(150*(adjustor+1), levelOfCurNode*70, Lines.get(nodeNumber), Integer.toString(nodeNumber));
            map.put(nodeNumber, curNode);
            root.getChildren().add(curNode);
        }
        
        File file3 = new File("F:\\Downloads\\CFG-master\\Edges.txt");
        Scanner scan3 = new Scanner(file3);
        while(scan3.hasNextInt()){
            
            int source = scan3.nextInt();
            int dest = scan3.nextInt();
            
            GraphicNode tmp = map.get(source);
            //System.out.println(tmp.centerXProperty());
            //System.out.println("X " + source + " " + dest);

            Edge connection = new Edge(map.get(source),map.get(dest));
            connection.setStroke(Color.CYAN);
            connection.setStrokeWidth(5);
            root.getChildren().add(0, connection);
        }
    }

    public void main(String... args) {
        launch(args);
    }
}
