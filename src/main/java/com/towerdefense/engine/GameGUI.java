package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//import javafx.scene.layout.Pane;

public class GameGUI extends GUI{

    private HealthBar healthbar;
    private Text money;

    public GameGUI() {
        super();
    }

    void drawGui(){
        healthbar = new HealthBar(this.getLayer()); 
        healthbar.setStartHealth(5);
        /*
        create money label
        */
        money = new Text("Money: "+Settings.MONEY);
        money.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        money.setTranslateX(Settings.SCENE_WIDTH - 100);
        money.setTranslateY(35);
        GuiHandler.getLayerPane().getChildren().addAll(money);
    }
    
    public HealthBar getHealthBar(){
        return healthbar;
    }

    public void updateMoney(){
        money.setText("Money: "+Settings.MONEY);
    }
}