package com.towerdefense.engine;

import javafx.scene.layout.Pane;

public class GameGUI extends GUI{

    private HealthBar healthbar;

    public GameGUI() {
        super();
    }

    void drawGui(){
        healthbar = new HealthBar(this.getLayer()); 
    }
    
    public HealthBar getHealthBar(){
        return healthbar;
    }
}
