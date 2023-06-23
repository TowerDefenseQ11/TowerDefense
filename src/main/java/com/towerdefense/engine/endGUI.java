package com.towerdefense.engine;

import com.towerdefense.map.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class endGUI extends GUI{

    public endGUI() {
        super();
    }

<<<<<<< HEAD
    @Override
    void drawGui()
    {
        
        Text endText = new Text("Game Over");
        endText.setFont(Font.font("Arial", 20));
    
    
        Button startAgainButton = new Button("");

        startAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                 
                /*@Override public void handle(ActionEvent e) {
                StartGUI.drawStartGUI(Pane layerPane); //this.getLayer()


                }*/
            }
        });
=======
@Override
  void drawGui(Pane layerPane)
    {
        Text endText = new Text("Game Over");
        endText.setFont("Arial", 20);
    
    
    Button startAgainButton = new Button("");
    startAgainButton.setOnAction(new EventHandler <ActionEvent>)
    {
            @Override public void handle(ActionEvent e) {
                StartGUI.drawStartGUI(Pane layerPane);


                }
            });
        }
>>>>>>> c180babed2ee087dbf8e1b914141578c97ef040c
    }
           

}
