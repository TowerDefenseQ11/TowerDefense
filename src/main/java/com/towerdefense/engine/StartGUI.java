package com.towerdefense.engine;

import com.towerdefense.map.Map;
import javafx.scene.control.Button;
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
        Button startButton = new Button("START");

         /*
        style button
        */
        startButton.setDefaultButton(false);
        startButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 35));
        startButton.setTranslateX(225);
        startButton.setTranslateY(400);
        

        /*
        start game when clicking on the button
        */
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
        
            /*
            create game with enemies and weapons
            */
            GameGUI gameGUI = new GameGUI();
            GuiHandler.switchGui(gameGUI);
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
                skillTreeGUI skillTree = new skillTreeGUI(GuiHandler.getLayerPane());
                GuiHandler.switchGui(skillTree);
            }
        });
        /*
        style skillTreeButton
        */
        /*skillTreeButton.setFont();
        startButton.setTranslate;
        startButton.setTranslateY();*/
        
        this.getLayer().getChildren().addAll(startButton, skillTreeButton, title, text);
    }

}