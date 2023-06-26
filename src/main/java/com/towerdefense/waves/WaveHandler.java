package com.towerdefense.waves;

public class WaveHandler {

    /*
    *  Class that manage the different waves
    */

     public static WaveTypes CURRENT_WAVE;
     private EnemySpawningGroup[] enemySpawningGroups;
     private static double wavePercent;


     public WaveHandler () {
         //Start wave
         CURRENT_WAVE = WaveTypes.WAVE_1;
         this.enemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
     }

     /*
      *  Updates the wave and changes
      */
     public void changeWave(WaveTypes wave) {
        CURRENT_WAVE = wave;
        this.enemySpawningGroups = CURRENT_WAVE.getEnemySpawningGroups();
     }

     public void start() {

     }
    /*
     *  Updates the percent of a wave;
     */
     public static void changePercent(double currentPercent) {
         wavePercent = currentPercent;
     }
}
