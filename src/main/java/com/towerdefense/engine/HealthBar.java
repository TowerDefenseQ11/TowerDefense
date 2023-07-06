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
    public double startHealth;

    public HealthBar(Layer guiLayer){ //healthbar for gui
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

 
    public HealthBar(Pane guiLayer){ //healthbar for enemies
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

    public void setStartHealth(double startHealth){
        this.startHealth = startHealth;
        this.health = startHealth;
    }

    public void updateHealthBar(){
        health--;
        System.out.println(health + " / " + startHealth);
        int width = (int) (health/startHealth * maxHealthPixel);
        foreground.setWidth(width);
    }

    public void updateHealthBar(double health){
        System.out.println(health + " / " + startHealth);
        this.health = health;
        int width = (int) (health/startHealth * maxHealthPixel);
        foreground.setWidth(width);
    }

}
