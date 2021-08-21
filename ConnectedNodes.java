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
import javafx.geometry.Point2D;
import javafx.util.Pair;

class graphicNode extends Circle {
    public double dragBaseX;
    public double dragBaseY;

    public graphicNode(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCenterX(event.getSceneX() - dragBaseX);
                setCenterY(event.getSceneY() - dragBaseY);
            }
        });
    }
}

class Edge extends Line {
    public Edge(graphicNode startNode, graphicNode endNode) {
        startXProperty().bind(startNode.centerXProperty());
        startYProperty().bind(startNode.centerYProperty());        
        endXProperty().bind(endNode.centerXProperty());
        endYProperty().bind(endNode.centerYProperty());        
    }
}

public class ConnectedNodes extends Application {

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
        HashMap<Integer, graphicNode> map = new HashMap<>();
        
        numberOfNodes = scan.nextInt();
        System.out.println(numberOfNodes);
        
        for(int nodeNumber = 2; nodeNumber<=numberOfNodes+1; nodeNumber++){
            graphicNode curNode = new graphicNode(rnd.nextDouble()*1400,rnd.nextDouble()*400,R);
            if(nodeNumber==2) curNode.setFill(Color.BLUE);
            else  curNode.setFill(Color.RED);
            map.put(nodeNumber, curNode);
            root.getChildren().add(curNode);
        }
        while(scan.hasNextInt()){
            
            int source = scan.nextInt();
            int dest = scan.nextInt();
            
            graphicNode tmp = map.get(source);
            //System.out.println(tmp.centerXProperty());
            //System.out.println(source + " " + dest);

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
