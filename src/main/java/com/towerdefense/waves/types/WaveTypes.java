package com.towerdefense.waves.types;

import com.towerdefense.enemy.type.EnemyType;

public enum WaveTypes {

    WAVE_1("Start", new EnemySpawningGroup[]{new EnemySpawningGroup(EnemyType.ENEMY_1, 30)});

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

