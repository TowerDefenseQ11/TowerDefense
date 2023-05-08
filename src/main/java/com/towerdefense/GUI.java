package com.towerdefense;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GUI
{
    public GUI(Pane layerPane) {
    }

    public void drawGui(Pane layerPane)
    {
        Text title = new Text("Tower Defense");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        title.setTranslateX(110);
        title.setTranslateY(175);

        Text text = new Text("Press Enter to start");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        text.setTranslateX(175);
        text.setTranslateY(525);

        Button startButton = new Button("START");
        startButton.setDefaultButton(false);
        startButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 35));
        startButton.setTranslateX(225);
        startButton.setTranslateY(400);
        layerPane.getChildren().addAll(startButton, title, text);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                 
            //create background
            Tilemap tilemap = new Tilemap(layerPane);
        
            //create game with enemies and weapons
            Game game = new Game(layerPane);
            }
        });
    }

}