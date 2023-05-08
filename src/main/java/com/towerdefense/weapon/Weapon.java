package com.towerdefense.weapon;

import com.towerdefense.Settings;
import com.towerdefense.weapon.bullet.Bullet;
import com.towerdefense.engine.Layer;
import com.towerdefense.engine.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

/*
 * TowerDefense weapon that destroys enemies
 */
public class Weapon {
    private Image image;
    private ImageView imageView;
    private int damage;
    private Vector2D location;
    private Vector2D lastTarget;
    private Vector2D velocity = new Vector2D(0, 0);
    private double angle = 0;
    private Layer playerfield;

    private double maxForce = Settings.WEAPON_MAX_FORCE;
    private double maxSpeed = Settings.WEAPON_MAX_SPEED;

    public Weapon(int x, int y, Layer layer){
        this.playerfield = layer;
        damage = 10;

        location = new Vector2D(x, y);

        image = new Image("weapon_1.png");
        imageView = new ImageView(image);
        imageView.relocate(x, y);
        imageView.setRotate(angle);

        layer.getChildren().add(imageView);

        spawnBullets();
    }

    /*
     * rotate weapon image to enemy / target smoothly
     */
    public void rotateTo(Vector2D target){
        Vector2D desired = Vector2D.subtract(target, location); 
        angle = desired.heading2D();

        if(lastTarget != null){
            desired.normalize();
            desired.multiply(maxSpeed);
            Vector2D steer = Vector2D.subtract(desired, velocity);
            steer.limit(maxForce);

            velocity.add(steer);
            velocity.limit(maxSpeed);
            angle = velocity.heading2D();
        }

        
        imageView.setRotate(Math.toDegrees(angle)+90);
        lastTarget = desired;
    }

    public Vector2D getLocation(){
        return location;
    }

    /*
     * spawn bullet every x seconds
     */
    private void spawnBullets(){
        
        Timeline bulletSpawnTimeline = new Timeline(
                 new KeyFrame(Duration.seconds(Settings.BULLET_SPAWN_TIME), 
                 new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new Bullet((int) location.x, (int) location.y, (int) Math.toDegrees(angle), playerfield);
            }
        }));
        bulletSpawnTimeline.setCycleCount(Timeline.INDEFINITE);
        bulletSpawnTimeline.play();
    }
}
