package com.towerdefense.enemy.handler;

import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.engine.Layer;
import com.towerdefense.engine.Vector2D;
import com.towerdefense.enemy.Enemy;
import javafx.scene.Scene;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/*
 * handle all enemies and update position smoothly
 */
public class EnemyHandler {

    private Layer playfield;
    static Random random = new Random();
    private List<Enemy> allEnemies = new ArrayList<>();
    private Scene scene;
    private int[][] mapPos;

    public EnemyHandler(Layer playfield, int[][] mapPos) {
        // playfield for enemies
        this.playfield = playfield;
        this.mapPos = mapPos;
    }

    /*
     * update position of each enemy smoothly in new thread -> gameLoop
     */
    public void updateMove() {

        // seek attractor location, apply force to get towards it
        allEnemies.forEach(vehicle -> { //todo: fix java.util.ConcurrentModificationException
            vehicle.seek();
        });

        // move sprite
        allEnemies.forEach(Enemy::move);

        // update in fx scene
        allEnemies.forEach(Enemy::display);
        allEnemies.forEach(Enemy::display);
    }

    /**
     * enemy finished path or died
     */
    public void destroyEnemy(Enemy enemy) {
        if (allEnemies.contains(enemy)) {
            playfield.getChildren().remove(enemy);
            allEnemies.remove(enemy);
        }
    }

    /**
     * Add single vehicle to list of vehicles and to the playfield
     */
    public void addEnemy(EnemyType enemyType) {
        // create sprite and add to layer
        Enemy enemy = new Enemy(playfield, this, mapPos, enemyType);

        // register vehicle
        allEnemies.add(enemy);

    }

    /*
     * return last spawned enemy
     */
    public Enemy getLastEnemy() {
        return allEnemies.get(allEnemies.size() - 1);
    }

    /*
     * return nearest enemy to weaponPos
     */
    public Enemy getNearestEnemy(Vector2D weaponPos) {
        Enemy nearestEnemy = null;
        double nearestDistance = 0;

        for (int i = 0; i < allEnemies.size(); i++) {
            Vector2D desired = Vector2D.subtract(weaponPos, allEnemies.get(i).getLocation());
            // The distance is the magnitude of the vector pointing from location to target.
            double distance = desired.magnitude();
            if (
                    (nearestDistance == 0 || distance < nearestDistance) &&
                            allEnemies.get(i) != null

            ) {
                nearestDistance = distance;
                nearestEnemy = allEnemies.get(i);
            }
        }
        return nearestEnemy;
    }

    public List<Enemy> getAllEnemies() {
        return allEnemies;
    }

    /*
     * updates size and position of all weapons
     */
    public void updateResponsiveSize(){
        allEnemies.forEach(enemy -> {
            enemy.updateResponsiveSize();
        });
    }
}


