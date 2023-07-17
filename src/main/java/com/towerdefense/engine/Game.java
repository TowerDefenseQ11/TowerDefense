package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.map.Map;
import com.towerdefense.waves.handler.WaveHandler;
import com.towerdefense.weapon.Weapon;
import com.towerdefense.weapon.handler.WeaponHandler;
import com.towerdefense.weapon.type.TowerType;
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

    private List<int[]> mapPosList;
    private EnemyHandler enemyHandler;
    private WeaponHandler weaponHandler;
    private Layer playfield;
    private Layer popupLayer;
    public static WaveHandler waveHandler;
    private Timeline enemySpawnTimeline;
    private AnimationTimer gameLoop;
    private boolean isPlaying = true;
    private int money;

    public Game(Pane layerPane) {
        Map map = new Map(layerPane, this);
        this.mapPosList = map.getMapPosList();
        this.money = Settings.MONEY;

        //create new game layer
        playfield = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(playfield);
        playfield.setPickOnBounds(false);

        //add first enemy
        enemyHandler = new EnemyHandler(playfield, mapPosList, this);
        enemyHandler.addEnemy(EnemyType.ENEMY_1);
        weaponHandler = new WeaponHandler(playfield, enemyHandler);

        popupLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(popupLayer);
        popupLayer.setPickOnBounds(false);
        waveHandler = new WaveHandler(layerPane);
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
    public void createWeapon(int x, int y, TowerType towerType) {
        System.out.println(x + "; " + y);
        hideTowerPopup();
        weaponHandler.addWeapon(x, y, towerType);
        this.money -= towerType.getMoney();
        GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
        gameGUI.updateMoney();
    }

    /*
     * hides tower popup
     */
    public void hideTowerPopup(){
        popupLayer.setPickOnBounds(false);
        popupLayer.getChildren().clear();
    }

    void startGame() {

        // start game
        gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (GuiHandler.getGUI() instanceof GameGUI) {
                    GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
                    endGame(gameGUI.getHealthBar().getHealth());
                }else if (GuiHandler.getGUI() instanceof EndGUI)
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


        enemySpawnTimeline = new Timeline(
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

    /*
     * end game when health is equal or below 0
     */
    public void endGame(double health) {
        if (health <= 0) {
            GuiHandler.switchGui(new EndGUI());
        }
    }

    /*
     * quit game: stop all timelines
     */
    public void quit(){
        System.out.println("quit game");
        gameLoop.stop();
        gameLoop = null;

        enemySpawnTimeline.stop();
        enemySpawnTimeline = null;
        
        enemyHandler.killEnemies();
        enemyHandler = null;
        
        weaponHandler.killTowers();
        weaponHandler = null;
    }

    /*
     * toggle pause and play game
     */
    public boolean pauseOrPlayButtonClicked(){
        isPlaying = !isPlaying;
        if(isPlaying){
            play();
        }else{
            pause();
        }
        return isPlaying;
    }

    /*
     * pause all animation timelines
     */
    private void pause(){
        gameLoop.stop();
        enemySpawnTimeline.pause();
        enemyHandler.pauseEnemies();
        weaponHandler.pauseTowers();
    }

    /*
     * start all animation timelines
     */
    private void play(){
        gameLoop.start();
        enemySpawnTimeline.play();
        enemyHandler.playEnemies();
        weaponHandler.playTowers();
    }

    public int getMoney(){
        return this.money;
    }

    public void addMoney(int num){
        this.money += num;
        GameGUI gameGUI = (GameGUI) GuiHandler.getGUI();
        gameGUI.updateMoney();
    }
}