package com.towerdefense.engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

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
        title.setTranslateX(250);
        title.setTranslateY(100);


        /*
         * create button
         */
        Button backButton = new Button ("BACK");

        /*
         * style button
         */
        backButton.setFont(Font.font("Arial, 20"));
        backButton.setTranslateX(0);
        backButton.setTranslateY(0);

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
        Button startButton = new Button("START");

        /*
         * style button
         */
        startButton.setDefaultButton(false);
        startButton.setFont(Font.font("Arial", 20));
        startButton.setTranslateX(550);
        startButton.setTranslateY(0);
        
        /*
         * start game when clicking startButton
         */
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GuiHandler.switchGui(new GameGUI());
            }
        });


        /*
         * add objects to layer
         */
        this.getLayer().getChildren().addAll(title, backButton, startButton);
    }
    
}
