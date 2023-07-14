package com.towerdefense.weapon;

import com.towerdefense.Settings;
import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.weapon.bullet.Bullet;
import com.towerdefense.engine.Layer;
import com.towerdefense.engine.Vector2D;
import com.towerdefense.weapon.type.TowerType;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;



/*
 * TowerDefense weapon that destroys enemies
 */
public class Weapon {
    private Image image;
    private ImageView imageView;
    private ImageView backgroundView;
    private int damage;
    private Vector2D location;
    private Vector2D lastTarget;
    private Vector2D velocity = new Vector2D(0, 0);
    private double angle = 0;
    private Layer playerfield;
    private boolean isShooting;
    private Random random = new Random();
    private Timeline bulletSpawnTimeline;
    private double maxForce = Settings.WEAPON_MAX_FORCE;
    private double maxSpeed = Settings.WEAPON_MAX_SPEED;
    private int x, y;
    private Image[] sprites;
    private int currentImageIndex = 0;
    private EnemyHandler enemyManager;
    private TowerType towerType;

    public Weapon(int x, int y, Layer layer, EnemyHandler enemyManager, TowerType towerType){
        this.x = x;
        this.y = y;
        this.playerfield = layer;
        this.enemyManager = enemyManager;
        this.towerType = towerType;
        this.damage = 10;

        location = new Vector2D(x, y);

        //Image background = null; new Image("Tower1Base.png", 64, 64, false, false);
        //backgroundView = new ImageView(background);
        //backgroundView.relocate(x, y);

        image = new Image(
            this.getClass().getResourceAsStream("/"+towerType.getTowerFolder()+"/sprite_00.png"),
            64, 64, false, false); //weapon_1
        imageView = new ImageView(image);
        imageView.relocate(x, y);
        imageView.setRotate(angle);

        //layer.getChildren().add(backgroundView);
        layer.getChildren().add(imageView);
        animateView();

        spawnBullets();
    }

    private void animateView(){
        String folderName = "/"+towerType.getTowerFolder()+"/";
        int lastIndex = 4;
        sprites = new Image[lastIndex];

        for(int i=0; i<lastIndex; i++){
            String zero = i>=10 ? "" : "0";
            String path = folderName+"sprite_"+zero+i+".png";
            sprites[i] = new Image(
                this.getClass().getResourceAsStream(path), 64, 64, false, false
            );
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Settings.FRAME_DURATION, event -> {
                    // Nächstes Bild anzeigen
                    currentImageIndex = (currentImageIndex + 1) % sprites.length;
                    imageView.setImage(sprites[currentImageIndex]);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /*
     * rotate weapon image to enemy / target smoothly
     */
    public void rotateTo(Vector2D target){
        isShooting = true;
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
        
        bulletSpawnTimeline = new Timeline(
                 new KeyFrame(Duration.seconds(Settings.BULLET_SPAWN_TIME + Math.random() * Settings.BULLET_RANDOM_SPAWN_TIME), 
                 new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //check if enemy is reached
                if(!isShooting){
                    return;
                }
                
                new Bullet((int) location.x, (int) location.y, (int) Math.toDegrees(angle), playerfield, enemyManager);
            }
        }));
        bulletSpawnTimeline.setCycleCount(Timeline.INDEFINITE);
        bulletSpawnTimeline.play();
    }

    public void setShooting(boolean isShooting){
        this.isShooting = isShooting;
    }

    /*
     * fit size and position to cell width
     */
    public void updateResponsiveSize(){
        imageView.setFitWidth(Settings.getResponsiveTileWidth());
        imageView.setFitHeight(Settings.getResponsiveTileWidth());
        imageView.relocate(x*Settings.getResponsiveTileWidth(), y*Settings.getResponsiveTileWidth());

        backgroundView.setFitWidth(Settings.getResponsiveTileWidth());
        backgroundView.setFitHeight(Settings.getResponsiveTileWidth());
        backgroundView.relocate(x*Settings.getResponsiveTileWidth(), y*Settings.getResponsiveTileWidth());

        location = new Vector2D(x*Settings.getResponsiveTileWidth(), y*Settings.getResponsiveTileWidth());
    }

}
