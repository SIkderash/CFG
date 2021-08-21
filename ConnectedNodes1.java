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
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Scanner;
import static javafx.application.Application.launch;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.util.Pair;

class graphicNode1 extends Button {
    public double dragBaseX;
    public double dragBaseY;

    public graphicNode1(double centerX, double centerY, double radius) {
        //super(centerX, centerY, radius);
        setLayoutX(centerX);
        setLayoutY(centerY);
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setLayoutX(event.getSceneX() - dragBaseX);
                setLayoutY(event.getSceneY() - dragBaseY);
            }
        });
    }
}

class Edge1 extends Line {
    public Edge1(graphicNode1 startNode, graphicNode1 endNode) {
        startXProperty().bind(startNode.layoutXProperty());
        startYProperty().bind(startNode.layoutYProperty());        
        endXProperty().bind(endNode.layoutXProperty());
        endYProperty().bind(endNode.layoutYProperty());        
    }
}

public class ConnectedNodes1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        initGraph(root);

        Scene scene = new Scene(root, 1800, 800);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initGraph(Group root) throws FileNotFoundException {
        File file = new File("F:\\Downloads\\CFG-master\\Edges.txt");
        Scanner scan = new Scanner(file);
        
        double R = 20;
        Random rnd = new Random();
        
        int numberOfNodes = 0;
        HashMap<Integer, graphicNode1> map = new HashMap<>();
        
        numberOfNodes = scan.nextInt();
        System.out.println(numberOfNodes);
        
        for(int nodeNumber = 2; nodeNumber<=numberOfNodes+1; nodeNumber++){
            graphicNode1 curNode = new graphicNode1(rnd.nextDouble()*1400,rnd.nextDouble()*400,R);
            curNode.setText(Integer.toString(nodeNumber));
            map.put(nodeNumber, curNode);
            root.getChildren().add(curNode);
        }
        while(scan.hasNextInt()){
            
            int source = scan.nextInt();
            int dest = scan.nextInt();
            
            graphicNode1 tmp = map.get(source);
            //System.out.println(tmp.centerXProperty());
            //System.out.println(source + " " + dest);

            Edge1 connection = new Edge1(map.get(source),map.get(dest));
            connection.setStroke(Color.CYAN);
            connection.setStrokeWidth(5);
            root.getChildren().add(0, connection);
        }
    }

    public void main(String... args) {
        launch(args);
    }
}
