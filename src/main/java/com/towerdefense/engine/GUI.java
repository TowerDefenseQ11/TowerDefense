package com.towerdefense.engine;

import com.towerdefense.map.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GUI
{
    public GUI(Pane layerPane) {
    }

    //show gui
    public void drawGui(Pane layerPane)
    {
        //create title
        Text title = new Text("Tower Defense");
        //style title
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        title.setTranslateX(110);
        title.setTranslateY(175);

        //create text under the button
        Text text = new Text("Press Enter to start");
        //style text
        text.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        text.setTranslateX(175);
        text.setTranslateY(525);

        //create start button
        Button startButton = new Button("START");
        //style button
        startButton.setDefaultButton(false);
        startButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 35));
        startButton.setTranslateX(225);
        startButton.setTranslateY(400);
        layerPane.getChildren().addAll(startButton, title, text);

        //start game when clicking on the button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                 
            //create background
            Map tilemap = new Map(layerPane);
        
            //create game with enemies and weapons
            Game game = new Game(layerPane);
            }
        });
    }

}