package com.towerdefense.enemy.type;

public enum EnemyType {

    ENEMY_1("Enemy1", 4.2, "enemy_2", 4, 3,3,1),
    ENEMY_2("Enemy2", 10, "enemy_2", 12, 3,3,1);

    private final String displayName;
    private final double startHealth;
    private final double startSpawnTime;
    private final double startForce;
    private final double startSpeed;
    private final String enemyFolder;
    private final int enemyImageCount;

    EnemyType(String displayName, double startHealth, String enemyFolder,  int enemyImageCount, double startSpawnTime, double startForce, double startSpeed) {
        this.displayName = displayName;
        this.startHealth = startHealth;
        this.enemyFolder = enemyFolder;
        this.enemyImageCount = enemyImageCount;
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

    public String getEnemyFolder() {
        return enemyFolder;
    }
    public int getEnemyImageCount() {
        return enemyImageCount;
    }

    public String getDisplayName() {
        return displayName;
    }
}
