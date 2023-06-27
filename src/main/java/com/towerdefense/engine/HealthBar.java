package com.towerdefense.engine;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class HealthBar {
    private ImageView background;
    private Rectangle foreground;
    private int health;
    private int maxHealthPixel;

    public HealthBar(Layer guiLayer){
        health = 100;
        maxHealthPixel = 200;
        foreground = new Rectangle(25, 25, maxHealthPixel, 15);
        foreground.setFill(Color.GREEN);
        guiLayer.getChildren().add(foreground);
    }

    public void updateHealthBar(){
        health--;
        int width = health/100 * maxHealthPixel;
        foreground.setWidth(width);
    }
}
