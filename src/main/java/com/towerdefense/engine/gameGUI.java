package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.scene.layout.Pane;

public class GameGUI extends GUI{

    private HealthBar healthbar;

    public GameGUI() {
        super();
    }

    void drawGui(){
        Game game = new Game(GuiHandler.getLayerPane());
        Layer topLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        topLayer.setPickOnBounds(false);
        GuiHandler.getLayerPane().getChildren().addAll(topLayer);
        healthbar = new HealthBar(topLayer); 
        healthbar.setStartHealth(5);
    }
    
    public HealthBar getHealthBar(){
        return healthbar;
    }
}
