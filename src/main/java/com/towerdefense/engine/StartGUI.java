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
    show gui
    */
    public void drawGui()
    {
        /*
        create title
        */
        Text title = new Text("Tower Defense");

        /*
        style title
        */
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        title.setTranslateX(110);
        title.setTranslateY(175);

        /*
        create text under the button
        */
        Text text = new Text("Press Enter to start");

        /*
        style text
        */
        text.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        text.setTranslateX(175);
        text.setTranslateY(525);

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

        startButton.setTranslateX(Settings.SCENE_WIDTH/2 - Settings.getResponsiveTileWidth()/2);
        startButton.setTranslateY(Settings.SCENE_HEIGHT/2 - Settings.getResponsiveTileWidth()/2);

        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GuiHandler.switchGui(new GameGUI());
            }
        });



        
        
        Button skillTreeButton = new Button("Skill Tree");
        /* 
        style skillTreeButton
        */
        skillTreeButton.setFont(Font.font("Arial", 20));
        skillTreeButton.setTranslateX(400);
        skillTreeButton.setTranslateY(550);

        skillTreeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                GuiHandler.switchGui(new SkillTreeGUI());
            }
        });
        
        this.getLayer().getChildren().addAll(startButton, skillTreeButton, title, text);
    }

}