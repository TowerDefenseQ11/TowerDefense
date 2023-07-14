package com.towerdefense.enemy.type;

public enum EnemyType {

    ENEMY_1("Enemy1", 4.2, "EnemyTest.png",3,3,1),
    ENEMY_2("Enemy2", 10, "EnemyTest.png",2,6,2);

    private final String displayName;
    private final double startHealth;
    private final double startSpawnTime;
    private final double startForce;
    private final double startSpeed;
    private final String enemyImage;

    EnemyType(String displayName, double startHealth, String enemyImage, double startSpawnTime, double startForce, double startSpeed) {
        this.displayName = displayName;
        this.startHealth = startHealth;
        this.enemyImage = enemyImage;
        this.startSpawnTime = startSpawnTime;
        this.startSpeed = startSpeed;
        this.startForce = startForce;
    }

    public double getStartSpeed() {
        return startSpeed;
    }

    public double getStartSpawnTime() {
        return startSpawnTime;
    }

    public double getStartForce() {
        return startForce;
    }

    public double getStartHealth() {
        return startHealth;
    }

    public String getEnemyImage() {
        return enemyImage;
    }

    public String getDisplayName() {
        return displayName;
    }
}
