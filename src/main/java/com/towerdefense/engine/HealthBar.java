package com.towerdefense.engine;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

import com.towerdefense.Settings;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class HealthBar {
    private Rectangle background;
    private Rectangle foreground;
    public double health;
    private int maxHealthPixel;
    private double startHealth;

    public HealthBar(Layer guiLayer){ //healthbar for gui
        startHealth = 100;
        health = 100;
        maxHealthPixel = 200;

        background = new Rectangle(25, 25, maxHealthPixel, 15);
        background.setFill(Color.GREY);
        guiLayer.getChildren().add(background);

        foreground = new Rectangle(25, 25, maxHealthPixel, 15);
        foreground.setFill(Color.GREEN);
        guiLayer.getChildren().add(foreground);
    }

 
    public HealthBar(Pane guiLayer){ //healthbar for enemies
        startHealth = 100;
        maxHealthPixel = 100;

        background = new Rectangle(-16, -8, maxHealthPixel, 5);
        background.setFill(Color.GREY);
        guiLayer.getChildren().add(background);


        foreground = new Rectangle(
            -16, 
            -8,
            maxHealthPixel, 5
        );
        foreground.setFill(Color.GREEN);
        guiLayer.getChildren().add(foreground);
    }

    public void setStartHealth(double startHealth) {
        health = startHealth;
    }

    public void updateHealthBar(){
        health--;
        System.out.println(health + " / " + startHealth);
        int width = (int) (health/startHealth * maxHealthPixel);
        foreground.setWidth(width);
    }

    public void updateHealthBar(double updateHealth){
        System.out.println(health + " / " + startHealth);
        health = updateHealth;
        int width = (int) (health/startHealth * maxHealthPixel);
        foreground.setWidth(width);
    }

    public double getHealth(){
        return health;
    }
}
