package com.towerdefense.engine;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class endGUI extends GUI{

    public endGUI() {
        super();
    }

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
    }
           

}
