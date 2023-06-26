package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.weapon.Weapon;
import com.towerdefense.weapon.WeaponHandler;
import com.towerdefense.enemy.Enemy;
import com.towerdefense.enemy.handler.EnemyHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.List;

/*
 * spawn enemies and weapons
 */
public class Game {

    private int[][] mapPos = { //path for enemies to follow 
        {-1, 4},
        {0, 4},
        {1, 4},
        {2, 4},
        {3, 4},
        {3, 3},
        {3, 2},
        {3, 1},
        {4, 1},
        {5, 1},
        {6, 1},
        {6, 2},
        {6, 3},
        {6, 4},
        {6, 5},
        {6, 6},
        {6, 7},
        {7, 7},
        {8, 7},
        {9, 7},
        {10, 7},
        
    };

    private EnemyHandler enemyManager;
    private WeaponHandler weaponHandler;
    private Layer playfield;

    public Game(Pane layerPane){
        //create new game layer
        playfield = new Layer( Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(playfield);

        //add first enemy
        enemyManager = new EnemyHandler(playfield, mapPos);
        enemyManager.addEnemy(EnemyType.ENEMY_1);

        //add demo weapons
        weaponHandler = new WeaponHandler(playfield, enemyManager);
        weaponHandler.addWeapon(0, 5);
        weaponHandler.addWeapon(1, 5);
        weaponHandler.addWeapon(2, 5);
        weaponHandler.addWeapon(3, 5);

        weaponHandler.addWeapon(5, 5);
        weaponHandler.addWeapon(5, 6);
        weaponHandler.addWeapon(5, 7);

        //start game loop
        startGame();

    }

    void startGame() {

        // start game
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                //move enemies smoothly
                enemyManager.updateMove(); 
                
                //rotate all weapons 
                List<Weapon> weapons = weaponHandler.getAllWeapons();
                for(int i=0; i<weapons.size(); i++){
                    Weapon weapon = weapons.get(i);
                    //get nearest enemy of weapon to rotate to
                    Enemy target = enemyManager.getNearestEnemy(weapon.getLocation()); 
                    if(
                        target != null && 
                        Vector2D.subtract(target.getLocation(), weapon.getLocation() ).magnitude() > Settings.BULLET_MAX_DISTANCE
                    ){
                        weapon.rotateTo(target.getLocation());
                    }else{
                        weapon.setShooting(false);
                    }
                }


            }
        };

        gameLoop.start();
        
        Timeline enemySpawnTimeline = new Timeline(
                 new KeyFrame(Duration.seconds(Settings.ENEMY_SPAWN_TIME), 
                 new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //spawn new enemy
                enemyManager.addEnemy(EnemyType.ENEMY_2);
            }
        }));
        enemySpawnTimeline.setCycleCount(Timeline.INDEFINITE);
        enemySpawnTimeline.play();


        

    }
}
