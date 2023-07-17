package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.map.Map;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartGUI extends GUI
{
    public StartGUI(){
        super();
    }

    /*
     *show gui
     */
    public void drawGui()
    {
        /*
         * create title
         */
        Text title = new Text("Tower Defense");

        /*
         *style title
         */
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        title.setTranslateX(110);
        title.setTranslateY(175);


        /*
        create start button
        */
        String path = "/GUI/startGUI/play.png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path), 
            64, 64, false, false
        );

        var startButton = new ImageView(img);

        startButton.setFitWidth(Settings.getResponsiveTileWidth());
        startButton.setFitHeight(Settings.getResponsiveTileWidth());

        /*
         * set location of startButton
         */
        startButton.setTranslateX(Settings.SCENE_WIDTH/2 - Settings.getResponsiveTileWidth()/2);
        startButton.setTranslateY(Settings.SCENE_HEIGHT/2 - Settings.getResponsiveTileWidth()/2);

        /*
         * start Game & show GameGUI when clicking on the button
         */
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GuiHandler.switchGui(new GameGUI());
            }
        });


        /*
         * create skillTreeButton
         */           
        Button skillTreeButton = new Button("Skill Tree");

        /* 
         *style skillTreeButton
         */
        skillTreeButton.setFont(Font.font("Arial", 20));
        skillTreeButton.setTranslateX(400);
        skillTreeButton.setTranslateY(550);

        /*
         * show SkillTreeGUI when clicking SkillTreeButton
         */
        skillTreeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                GuiHandler.switchGui(new SkillTreeGUI());
            }
        });
        

        /*
         * add objects to layer
         */
        this.getLayer().getChildren().addAll(startButton, skillTreeButton, title);
    }

}