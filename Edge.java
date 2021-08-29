/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import javafx.scene.shape.Line;

/**
 *
 * @author Asus
 */
class Edge extends Line {
    public Edge(GraphicNode startNode, GraphicNode endNode) {
        startXProperty().bind(startNode.layoutXProperty());
        startYProperty().bind(startNode.layoutYProperty());        
        endXProperty().bind(endNode.layoutXProperty());
        endYProperty().bind(endNode.layoutYProperty());        
    }
}
