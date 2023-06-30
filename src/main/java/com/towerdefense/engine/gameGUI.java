package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameGUI extends GUI{

    private HealthBar healthbar;
    private Text money;

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

        /*
        create money label
        */
        money = new Text("Money: "+Settings.MONEY);
        money.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        money.setTranslateX(Settings.SCENE_WIDTH - 100);
        money.setTranslateY(35);
        GuiHandler.getLayerPane().getChildren().addAll(money);
    }

    public void updateMoney(){
        money.setText("Money: "+Settings.MONEY);
    }
    
    public HealthBar getHealthBar(){
        return healthbar;
    }
}
