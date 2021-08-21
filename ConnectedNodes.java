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
    public int[][] Adj;
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        initGraph(root);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initGraph(Group root) {
        
        double R = 20;
        Random rnd = new Random();
        for(int i=0; i<10; i++){
            graphicNode node1 = new graphicNode(rnd.nextDouble()*300,rnd.nextDouble()*300, R);
            node1.setFill(Color.RED);
            root.getChildren().add(node1);
            graphicNode node2 = new graphicNode(rnd.nextDouble()*300,rnd.nextDouble()*300, R);
            node2.setFill(Color.RED);
            root.getChildren().add(node2);

            Edge connection = new Edge(node1, node2);
            connection.setStroke(Color.CYAN);
            connection.setStrokeWidth(5);
            root.getChildren().add(0, connection);
        }
    }

    public static void main(String... args) {
        launch(args);
    }
}
