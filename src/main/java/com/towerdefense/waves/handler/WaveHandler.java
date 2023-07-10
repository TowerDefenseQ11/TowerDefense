package com.towerdefense.waves.handler;

import com.towerdefense.waves.WaveCallback;
import com.towerdefense.waves.types.EnemySpawningGroup;
import com.towerdefense.waves.types.WaveTypes;

import java.lang.invoke.CallSite;

public class WaveHandler {

    /*
    *  Class that manage the different waves
    */

     public static WaveTypes CURRENT_WAVE;
     private EnemySpawningGroup[] enemySpawningGroups;
     private static double CURRENT_WAVE_PERCENT;


     public WaveHandler () {
         //Start wave
         CURRENT_WAVE = WaveTypes.WAVE_1;
         this.enemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
     }

     private boolean waveIsFinished() {
         return false;
     }

     /*
      *  Updates the wave and changes
      */
     public void changeWave(WaveTypes wave, WaveCallback<Boolean> waveCallback) {
         waveCallback.wave(true);
        CURRENT_WAVE = wave;
        this.enemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
     }

     public void start() {

     }
    /*
     *  Updates the percent of a wave;
     */
     public static void changePercent(double currentPercent) {
         CURRENT_WAVE_PERCENT = currentPercent;
     }
}
