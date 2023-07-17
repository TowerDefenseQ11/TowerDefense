package com.towerdefense.engine;

import com.towerdefense.Settings;
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
    {   /*
         * create text
         */
        Text endText = new Text("Game Over");

        /*
         * style text
         */
        endText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
        endText.setTranslateX(175);
        endText.setTranslateY(200);
    

        /*
         * create restartButton 
         */
        Button restartButton = new Button("Try Again");

        /*
         * style restartButton
         */
        restartButton.setFont(Font.font("Arial",FontWeight.BOLD, 30));
        restartButton.setTranslateX(Settings.SCENE_WIDTH / 3);
        restartButton.setTranslateY(Settings.SCENE_HEIGHT / 1.5);

        /*
         * go back to StartGUI when clicking restartButton
         */
        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e)
            {
                GuiHandler.switchGui(new StartGUI());
            }
        });


        /*
         * add objects to layer
         */
        this.getLayer().getChildren().addAll(endText, restartButton);
    }

}
