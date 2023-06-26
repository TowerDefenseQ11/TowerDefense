package com.towerdefense.waves;

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

class EnemySpawningGroup {
    private EnemyType enemyType;
    private int amount;

    public EnemySpawningGroup(EnemyType enemyType, int amount) {
        this.enemyType = enemyType;
        this.amount = amount;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public int getAmount() {
        return amount;
    }
}
