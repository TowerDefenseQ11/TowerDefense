package com.towerdefense.engine;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class skillTreeGUI extends GUI{

    public skillTreeGUI(Pane layerPane) {
        super();
    }

    void drawGui(){
        /*
         create title
         */
        Text title = new Text("Skill Tree");
        /*
        style title
        */
        title.setFont(Font.font("Arial", 30));
        title.setTranslateX(250);
        title.setTranslateY(100);

        /*
        create button
        */
        Button restartButton = new Button ("Start again");
        /*
        style button
         */
        restartButton.setFont(Font.font("Arial, 20"));
        restartButton.setTranslateX(0);
        restartButton.setTranslateY(0);

        this.getLayer().getChildren().addAll(title, restartButton);
    }
    
}
