package com.towerdefense.weapon.bullet;

import com.towerdefense.engine.Layer;
import com.towerdefense.Settings;
import com.towerdefense.enemy.Enemy;
import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.engine.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.AnimationTimer;

public class Bullet {
    private Image image;
    private ImageView imageView;
    private Vector2D location;
    private Vector2D startLocation;
    private double maxSpeed = Settings.BULLET_MAX_SPEED;
    private AnimationTimer loop;
    private Layer playerfield;
    private int angle;
    private EnemyHandler enemyManager;

    public Bullet(int x, int y, int angle, Layer layer, EnemyHandler enemyManager){
        this.playerfield = layer;
        this.angle = angle;
        this.enemyManager = enemyManager;

        location = new Vector2D(x, y);
        startLocation = new Vector2D(x, y);

        image = new Image("bullet.png");
        imageView = new ImageView(image);
        imageView.relocate(x, y);
        imageView.setRotate(angle);

        playerfield.getChildren().add(imageView);

        loop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                move();
                checkEnemy();
                checkDistance();
            }
            
        };
        loop.start();
    }

    private void checkEnemy(){
        for(Enemy enemy : enemyManager.getAllEnemies()){
            if (imageView.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                System.out.println("bullet hit enemy");
                enemy.damage(Settings.BULLET_Damage);
                destroy();
            }                
        }
        /*var bound = imageView.getBoundsInParent();
        for (iterable_type iterable_element : iterable) {
            if (imageView.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
                boolean collisionDetected = true;
            }
        }*/

    }

    private void move(){
        //move bullet smoothly
        Vector2D steering = new Vector2D(
            Math.cos(Math.toRadians(angle)), 
            Math.sin(Math.toRadians(angle))
        );
        steering.multiply(maxSpeed);
        location.add(steering);

        imageView.relocate(location.x, location.y);
    }

    private void checkDistance(){
        Vector2D subtract = Vector2D.subtract(location, startLocation);
        double distance = subtract.magnitude();
        if(distance > Settings.BULLET_MAX_DISTANCE){
            //destroy();
        }
    }

    private void destroy(){
        loop.stop();
        playerfield.getChildren().remove(imageView);
    }
}
