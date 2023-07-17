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
        endText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        endText.setTranslateX(175);
        endText.setTranslateY(200);
    
    
        Button restartButton = new Button("Try Again");
        restartButton.setFont(Font.font("Arial",FontWeight.BOLD, 30));
        restartButton.setTranslateX(225);
        restartButton.setTranslateY(400);

        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                StartGUI startGUI = new StartGUI();
                GuiHandler.switchGui(startGUI);
            }
        });

        this.getLayer().getChildren().addAll(endText, restartButton);
    }
    
           

}
