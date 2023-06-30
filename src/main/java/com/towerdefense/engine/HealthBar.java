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
    private double health;
    private int maxHealthPixel;
    private double startHealth;

    public HealthBar(Layer guiLayer){
        startHealth = 100;
        health = startHealth;
        maxHealthPixel = 200;

        background = new Rectangle(25, 25, maxHealthPixel, 15);
        background.setFill(Color.GREY);
        guiLayer.getChildren().add(background);

        foreground = new Rectangle(25, 25, maxHealthPixel, 15);
        foreground.setFill(Color.GREEN);
        guiLayer.getChildren().add(foreground);
    }


    public HealthBar(Pane guiLayer){
        health = 100;
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


    public void updateHealthBar(){
        health--;
        int width = (int) (health/100.0 * maxHealthPixel);
        foreground.setWidth(width);
    }

    public void setStartHealth(double startHealth){
        this.startHealth = startHealth;
    }

    public void updateHealthBar(double health){
        System.out.println(health + " / " + startHealth);
        this.health = health;
        int width = (int) (health/startHealth * maxHealthPixel);
        foreground.setWidth(width);
    }
}
