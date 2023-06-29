package com.towerdefense.engine;

import javafx.scene.layout.Pane;

public class GameGUI extends GUI{

    private HealthBar healthbar;

    public GameGUI() {
        super();
    }

    void drawGui(){
        healthbar = new HealthBar(this.getLayer()); 
        Game game = new Game(GuiHandler.getLayerPane());
    }
    
    public HealthBar getHealthBar(){
        return healthbar;
    }
}
