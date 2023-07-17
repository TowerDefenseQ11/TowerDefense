package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;

public class SkillTreeGUI extends GUI{

    public SkillTreeGUI() {
        super();
    }

    void drawGui(){
        /*
         * create title
         */
        Text title = new Text("Skill Tree");

        /*
         * style title
         */
        title.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD, 30));
        title.setTranslateX(Settings.SCENE_WIDTH / 2.5);
        title.setTranslateY(Settings.SCENE_HEIGHT / 10);


        /*
         * create button
         */
        Button backButton = new Button ("BACK");

        /*
         * style button
         */
        backButton.setFont(Font.font("Arial, 20"));
        backButton.setTranslateX(Settings.SCENE_WIDTH / 10);
        backButton.setTranslateY(Settings.SCENE_HEIGHT / 1.1);

        /*
         * go back to startGUI when clicking backButton
         */
        backButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                StartGUI startGUI = new StartGUI();
                GuiHandler.switchGui(startGUI);
            }
        });


        /*
         * create start button
         */
        String path = "/GUI/startGUI/play.png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path),
            64, 64, false, false
        );
        
        var startButton = new ImageView(img);
        startButton.setFitWidth(Settings.getResponsiveTileWidth());
        startButton.setFitHeight(Settings.getResponsiveTileWidth());

        startButton.setTranslateX(Settings.SCENE_WIDTH / 1.1 - Settings.getResponsiveTileWidth() / 2);
        startButton.setTranslateY(Settings.SCENE_HEIGHT / 1.1 - Settings.getResponsiveTileWidth() / 2);

        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GuiHandler.switchGui(new GameGUI());
            }
        });

        /*
         * add objects to layer
         */
        this.getLayer().getChildren().addAll(title, backButton, startButton);
    }
    
}
