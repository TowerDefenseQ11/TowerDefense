package com.towerdefense.enemy.type;

public enum EnemyType {

    ENEMY_1("Enemy1", 4.2, "enemy_1", 11, 10,0.1,1,10),
    ENEMY_2("Enemy2", 10, "enemy_2", 11, 4,3,1,10),
    ENEMY_3("Enemy3", 50, "enemy_3", 11, 5,3,1,10),
    ENEMY_4("Enemy4", 100, "enemy_4", 11, 6,3,0.5,10);
    

    private final String displayName;
    private final double startHealth;
    private final double startSpawnTime;
    private final double startForce;
    private final double startSpeed;
    private final String enemyFolder;
    private final int enemyImageCount;
    private final int money;

    EnemyType(String displayName, double startHealth, String enemyFolder,  int enemyImageCount, double startSpawnTime, double startForce, double startSpeed, int money) {
        this.displayName = displayName;
        this.startHealth = startHealth;
        this.enemyFolder = enemyFolder;
        this.enemyImageCount = enemyImageCount;
        this.startSpawnTime = startSpawnTime;
        this.startSpeed = startSpeed;
        this.startForce = startForce;
        this.money = money;
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
    public int getMoney(){
        return money;
    }
}
