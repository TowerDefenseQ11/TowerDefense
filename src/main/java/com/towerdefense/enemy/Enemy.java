package com.towerdefense.enemy;

import com.towerdefense.enemy.manager.EnemyManager;
import com.towerdefense.engine.Layer;
import com.towerdefense.Settings;
import com.towerdefense.engine.Vector2D;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends Region {

    private Vector2D location;
    private Vector2D velocity;
    private Vector2D acceleration;

    private double maxForce = Settings.ENEMY_MAX_FORCE;
    private double maxSpeed = Settings.ENEMY_MAX_SPEED;

    private Node view;

    // view dimensions
    private double width;
    private double height;
    private double centerX;
    private double centerY;
    private double radius;

    private double angle;

    private EnemyManager enemyManager;

    private int health;

    private int[][] mapPos;
    private int posIndex = 0;
    private Layer playerfield;

    public Enemy(Layer layer, EnemyManager enemyManager, int[][] mapPos) {
        this.enemyManager = enemyManager;
        this.mapPos = mapPos;
        this.playerfield = layer;

        this.location = new Vector2D(mapPos[0][0] * 64 + 32, mapPos[0][1] * 64 + 32);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
        this.width = 50;
        this.height = 25;
        this.centerX = width / 2;
        this.centerY = height / 2;

        health = 100;

        this.view = createView();

        setPrefSize(width, height);

        // add view to this node
        getChildren().add(view);

        // add this node to layer
        layer.getChildren().add(this);

    }

    public Node createView() {
        // Utils.createArrowImageView( (int) width);
        return new ImageView(new Image("EnemyTest.png", 64, 64, false, false)); //enemy_1
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

        if (posIndex >= mapPos.length) {
            enemyManager.destroyEnemy(this);
            return;
        }
        int x = mapPos[posIndex][0] * 64 + 32;
        int y = mapPos[posIndex][1] * 64 + 32;

        Vector2D target = new Vector2D(x, y);

        Vector2D desired = Vector2D.subtract(target, location);

        // The distance is the magnitude of the vector pointing from location to target.

        double d = desired.magnitude();
        desired.normalize();

        // reached target
        if (d < Settings.ENEMY_SLOW_DOWN_DISTANCE) {
            posIndex++;
        } else {
            desired.multiply(maxSpeed);
        }

        // The usual steering = desired - velocity
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);

        applyForce(steer);

    }

    /**
     * Update node position
     */
    public void display() {

        relocate(location.x - centerX, location.y - centerY);

        setRotate(Math.toDegrees(angle));

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
        if (health <= 0) {
            enemyManager.destroyEnemy(this);
        }
    }

    

}
