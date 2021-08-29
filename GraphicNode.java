/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Asus
 */
class GraphicNode extends Button {
    public double dragBaseX;
    public double dragBaseY;
    public String nodeText;
    public String nodeNumber;

    public GraphicNode(double centerX, double centerY, String nodeText, String nodeNo) {
        //super(centerX, centerY, radius);
        setLayoutX(centerX);
        setLayoutY(centerY);
        setText(nodeNo);
        this.nodeNumber = nodeNo;
        this.nodeText = nodeText;
        
        setOnMouseDragged((MouseEvent event) -> {
            setLayoutX(event.getSceneX() - dragBaseX);
            setLayoutY(event.getSceneY() - dragBaseY);
        });
        setOnMouseClicked((MouseEvent mouseEvent) -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    if(getText()==nodeNumber){
                        setText(nodeText);
                    }
                    else{
                        setText(nodeNumber);
                    }
                }
            }
        });
    }
}
