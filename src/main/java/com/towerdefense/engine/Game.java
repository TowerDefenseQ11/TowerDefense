package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.map.Map;
import com.towerdefense.waves.handler.WaveHandler;
import com.towerdefense.weapon.Weapon;
import com.towerdefense.weapon.handler.WeaponHandler;
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

    private EnemyHandler enemyHandler;
    private WeaponHandler weaponHandler;
    private Layer playfield;
    private Layer popupLayer;
    public static WaveHandler waveHandler;

    public Game(Pane layerPane) {
        new Map(layerPane, this);

        //create new game layer
        playfield = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(playfield);
        playfield.setPickOnBounds(false);

        //add first enemy
        enemyHandler = new EnemyHandler(playfield, mapPos);
        enemyHandler.addEnemy(EnemyType.ENEMY_1);
        weaponHandler = new WeaponHandler(playfield, enemyHandler);

        popupLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(popupLayer);
        popupLayer.setPickOnBounds(false);
        waveHandler = new WaveHandler();
        //start game loop
        startGame();

    }

    //todo: Put in Weapon Handler
    public void openCreateWeaponPopup(int x, int y) {
        popupLayer.setPickOnBounds(true);
        popupLayer.getChildren().clear();
        CreateWeaponPopup popup = new CreateWeaponPopup(x, y, popupLayer, this);
    }

    //todo: Put in Weapon Handler
    public void createWeapon(int x, int y) {
        System.out.println(x + "; " + y);
        popupLayer.setPickOnBounds(false);
        popupLayer.getChildren().clear();
        weaponHandler.addWeapon(x, y);
        Settings.MONEY -= Settings.TOWER_COST;
        GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
        gameGUI.updateMoney();
    }

    void startGame() {

        // start game
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (GuiHandler.getGUI() instanceof GameGUI) {
                    GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
                    endGame(gameGUI.getHealthBar().getHealth());
                } else if (GuiHandler.getGUI() instanceof EndGUI)
                    return;
                //move enemies smoothly
                enemyHandler.updateMove();

                //rotate all weapons 
                List<Weapon> weapons = weaponHandler.getAllWeapons();
                for (int i = 0; i < weapons.size(); i++) {
                    Weapon weapon = weapons.get(i);
                    //get nearest enemy of weapon to rotate to
                    Enemy target = enemyHandler.getNearestEnemy(weapon.getLocation());
                    if (
                            target != null &&
                                    Vector2D.subtract(target.getLocation(), weapon.getLocation()).magnitude() < Settings.BULLET_MAX_DISTANCE
                    ) {
                        weapon.rotateTo(target.getLocation());
                    } else {
                        weapon.setShooting(false);
                    }
                }
            }
        };

        gameLoop.start();


        Timeline enemySpawnTimeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               waveHandler.handleSpawnOfEnemy(enemyHandler);
                            }
                        }));
        enemySpawnTimeline.setCycleCount(Timeline.INDEFINITE);
        enemySpawnTimeline.play();
    }

    public void endGame(double health) {
        if (health <= 0) {
            EndGUI endGUI = new EndGUI();
            endGUI.drawGui();
            GuiHandler.switchGui(endGUI);
            System.out.println("END GUI");
        }
    }
}