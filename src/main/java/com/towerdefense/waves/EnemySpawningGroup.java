package com.towerdefense.waves;

import com.towerdefense.enemy.type.EnemyType;

public class EnemySpawningGroup {
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
