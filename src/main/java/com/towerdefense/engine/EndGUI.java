package com.towerdefense.engine;

import com.towerdefense.map.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class EndGUI extends GUI{

    public EndGUI() {
        super();
    }


    @Override
    void drawGui()
    {
        Text endText = new Text("Game Over");
        endText.setFont(Font.font("Arial", 20));
        endText.setTranslateX(250);
        endText.setTranslateY(200);
    
    
        Button restartButton = new Button("Try Again");
        restartButton.setFont(Font.font("Arial"));
        restartButton.setTranslateX(0);
        restartButton.setTranslateY(0);

        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                GuiHandler.switchGui(new StartGUI());
            }
        });

        this.getLayer().getChildren().addAll(endText, restartButton);
    }
    
           

}
