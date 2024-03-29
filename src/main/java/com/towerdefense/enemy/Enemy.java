package com.towerdefense.enemy;

import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.engine.*;

import java.io.Console;
import java.util.List;

import com.towerdefense.Settings;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Pane {

    private Vector2D location;
    private Vector2D velocity;
    private Vector2D acceleration;

    private ImageView view;

    // view dimensions
    private double width;
    private double height;
    private double centerX;
    private double centerY;
    private double radius;
    private double angle;
    private List<int[]> mapPosList;
    private int posIndex = 0;
    private Layer playerfield;

    private EnemyHandler enemyHandler;
    private EnemyType enemyType;
    private double maxForce;
    private double maxSpeed;
    private double health;
    private HealthBar healthBar;
    private Image[] sprites;
    private int currentImageIndex = 0;
    private Timeline timeline;

    private ImageView debugDesiredPositionImgView1;
    private ImageView debugDesiredPositionImgView2;

    public Enemy(Layer layer, EnemyHandler enemyHandler, List<int[]> mapPosList, EnemyType enemyType) {
        this.enemyHandler = enemyHandler;
        this.maxSpeed = enemyType.getStartSpeed();
        this.maxForce = enemyType.getStartForce();
        this.enemyType = enemyType;
        this.mapPosList = mapPosList;
        this.playerfield = layer;
        this.location = new Vector2D(mapPosList.get(0)[0] * 64, mapPosList.get(0)[1] * 64);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.width = 50;
        this.height = 25;
        this.centerX = 0; //width / 2;
        this.centerY = 0; //height / 2;

        health = enemyType.getStartHealth();
        healthBar = new HealthBar(this);
        healthBar.setStartHealth(health);

        this.view = createView();
        setPrefSize(width, height);
        // add view to this node
        getChildren().add(view);
        animateView();
        // add this node to layer
        layer.getChildren().add(this);


    }

    private void animateView(){
        String folderName = "/enemies/"+enemyType.getEnemyFolder()+"/";
        int lastIndex = enemyType.getEnemyImageCount();
        sprites = new Image[lastIndex];

        for(int i=0; i<lastIndex; i++){
            String zero = i>=10 ? "" : "0";
            String path = folderName+"sprite_"+zero+i+".png";
            sprites[i] = new Image(
                this.getClass().getResourceAsStream(path), 64, 64, false, false
            );
        }
        
       
        
        

        timeline = new Timeline(
                new KeyFrame(Settings.FRAME_DURATION, event -> {
                    // Nächstes Bild anzeigen
                    currentImageIndex = (currentImageIndex + 1) % sprites.length;
                    view.setImage(sprites[currentImageIndex]);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public ImageView createView() {
        String folderName = "/enemies/"+enemyType.getEnemyFolder()+"/";

        String path = folderName+"sprite_00.png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path), 64, 64, false, false
        );
        
        return new ImageView(img);
    }

    public ImageView createDebugImage() {
        String path = "/towers/tower_1/bullet.png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path), 64, 64, false, false
        );
        
        return new ImageView(img);
    }

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public void move() {
        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        angle = velocity.heading2D();

        // clear acceleration
        acceleration.multiply(0);
    }

    /**
     * Move sprite towards next target
     */
    public void seek() {
        if (posIndex >= mapPosList.size()) {
            //end of path reached
            if(!(GuiHandler.getGUI() instanceof GameGUI)){
                return;
            }

            GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
            HealthBar gameHealthBar = gameGUI.getHealthBar();
            if(gameHealthBar != null){
                gameHealthBar.updateHealthBar();
            }
            this.enemyHandler.destroyEnemy(this);
            return;
        }
        
        //x = index * 64 
        int x = mapPosList.get(posIndex)[0] * (int) Settings.getResponsiveTileWidth();
        //y = index * 64
        int y = mapPosList.get(posIndex)[1] * (int) Settings.getResponsiveTileWidth();

        Vector2D target = new Vector2D(x, y);
        Vector2D desired = Vector2D.subtract(target, location);

        // The distance is the magnitude of the vector pointing from location to target.
        double d = desired.magnitude();
        desired.normalize();

        //debugDesiredPosition(location, target);

        // reached target
        if (d < Settings.ENEMY_SLOW_DOWN_DISTANCE) {
            System.out.println(d + "<" + Settings.ENEMY_SLOW_DOWN_DISTANCE);
            posIndex++;
        } else {
            desired.multiply(maxSpeed);
        }

    

        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);
        applyForce(steer);
    }

    private void debugDesiredPosition(Vector2D v1, Vector2D v2){
        if(debugDesiredPositionImgView1 == null){
            debugDesiredPositionImgView1 = createDebugImage();
            playerfield.getChildren().add(debugDesiredPositionImgView1);
        }
         if(debugDesiredPositionImgView2 == null){
            debugDesiredPositionImgView2 = createDebugImage();
            playerfield.getChildren().add(debugDesiredPositionImgView2);
        }

        debugDesiredPositionImgView1.relocate(
            v1.x, v1.y
        );
        debugDesiredPositionImgView2.relocate(
            v2.x, v2.y
        );
    }

    /**
     * Update node position
     */
    public void display() {
        relocate(location.x - centerX, location.y - centerY);
        view.setRotate(Math.toDegrees(angle));
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        location.x = x;
        location.y = y;
    }

    public void setLocationOffset(double x, double y) {
        location.x += x;
        location.y += y;
    }

    public void damage(int hit) {
        health -= hit;
        healthBar.updateHealthBar(health);
        if (health <= 0) {
            enemyHandler.getGame().addMoney(enemyType.getMoney());
            this.enemyHandler.destroyEnemy(this);
            Game.waveHandler.handleDeathOfEnemy();
        }
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    /*
     * fit size and position to cell width
     */
    public void updateResponsiveSize() {
        view.setFitWidth(Settings.getResponsiveTileWidth());
        view.setFitHeight(Settings.getResponsiveTileWidth());

        this.width = Settings.getResponsiveTileWidth();
        this.height = Settings.getResponsiveTileWidth();
        this.centerX = width / 2;
        this.centerY = height / 2;
    }

    /*
     * kill enemie: stop animation timeline
     */
    public void kill(){
        System.out.println("kill enemy");
        timeline.stop();
        timeline = null;
        this.enemyHandler.destroyEnemy(this);
    }

    /*
     * stop animation timeline
     */
    public void pause(){
        timeline.pause();
    }

    /*
     * start animation timeline
     */
    public void play(){
        timeline.play();
    }
}
