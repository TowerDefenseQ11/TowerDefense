package com.towerdefense.waves.handler;

import com.towerdefense.Settings;
import com.towerdefense.enemy.Enemy;
import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.enemy.type.EnemyType;
import com.towerdefense.engine.GuiHandler;
import com.towerdefense.waves.types.EnemySpawningGroup;
import com.towerdefense.waves.types.WaveTypes;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WaveHandler {

    /*
     *  Class that manage the different waves
     */

    public static WaveTypes CURRENT_WAVE;
    private EnemySpawningGroup[] currentEnemySpawningGroups;
    private static double CURRENT_WAVE_PERCENT;
    private int enemyCount;
    private int enemyStartCount;
    private HashMap<EnemyType, Integer> enemyCache;


    public WaveHandler() {
        CURRENT_WAVE = WaveTypes.WAVE_1;
        this.currentEnemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
        changeWave(CURRENT_WAVE);
    }

    private boolean waveIsFinished() {
        return this.enemyCount == 0;
    }

    /*
     *  Updates the wave and changes
     */
    public void changeWave(WaveTypes wave) {

        CURRENT_WAVE = wave;
        currentEnemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
        enemyCache = new HashMap<>();

        if (waveIsFinished())
            start();

        for (EnemySpawningGroup enemySpawningGroup : currentEnemySpawningGroups) {
            enemyCount += enemySpawningGroup.getAmount();
            enemyCache.put(enemySpawningGroup.getEnemyType(), enemySpawningGroup.getAmount());
        }
        enemyCount--;
        enemyStartCount = enemyCount;


    }

    private void start() {
        System.out.println("TEXT ANIMATION DEBUG");
        Text waveText = new Text(WaveHandler.CURRENT_WAVE.getName());
        waveText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        waveText.setTranslateX(Settings.SCENE_WIDTH / 2);
        waveText.setTranslateY(Settings.SCENE_WIDTH / 2);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), waveText);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(3000), waveText);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.setCycleCount(1);
                fadeTransition2.setAutoReverse(false);
                fadeTransition2.play();
            }
        });
        fadeTransition.play();
        GuiHandler.getLayerPane().getChildren().addAll(waveText);
    }


    long duration = 0;

    public void handleSpawnOfEnemy(EnemyHandler enemyHandler) {
        if (enemyCache == null)
            return;

        duration++;
        this.enemyCache.forEach((enemyType, integer) -> {
            if (integer > 0) {
                if (duration >= (enemyType.getStartSpawnTime())) {
                    enemyCache.replace(enemyType, integer - 1);
                    enemyHandler.addEnemy(enemyType);
                    duration = 0L;
                }
            }
        });
    }

    /*
     *  Updates the percent of a wave;
     */
    public boolean handleDeathOfEnemy() {
        System.out.println("ENEMIES:" + this.enemyCount);
        if (!waveIsFinished()) {
            this.enemyCount--;
            CURRENT_WAVE_PERCENT = (double) this.enemyStartCount / enemyCount;
            return true;
        } else {
            String nameOfWave = CURRENT_WAVE.toString();
            int nextWave = 0;
            try {
                nextWave = Integer.parseInt(nameOfWave.split("_")[1]);
            } catch (Exception e) {
                System.out.println("Next wave not found because of name");
            }
            nextWave++;
            if (nextWave != 0) {
                WaveTypes waveTypes;
                try {
                    waveTypes = WaveTypes.valueOf("WAVE_" + nextWave);
                    this.changeWave(waveTypes);
                } catch (Exception e) {
                    System.out.println("Next Wave not found because of waveType");
                }
            }
            return false;
        }
    }
}
