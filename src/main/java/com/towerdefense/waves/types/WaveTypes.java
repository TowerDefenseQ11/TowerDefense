package com.towerdefense.waves.types;

import com.towerdefense.enemy.type.EnemyType;

public enum WaveTypes {

    WAVE_1("Start",   new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_1, 10)}),
    WAVE_2("Welle 2", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_2, 30)}),
    WAVE_3("Welle 3", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_3, 10)}),
    WAVE_4("Welle 4", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_4, 30)}),
    WAVE_5("Welle 5", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_4, 30)}),
    WAVE_6("Welle 6", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_4, 30)});

    private final String name;
    private final EnemySpawningGroup[] enemySpawningGroups;

    WaveTypes(String name, EnemySpawningGroup[] enemySpawningGroups) {
        this.name = name;
        this.enemySpawningGroups = enemySpawningGroups;
    }

    public EnemySpawningGroup[] getEnemySpawningGroups() {
        return enemySpawningGroups;
    }

    public String getName() {
        return name;
    }
}

