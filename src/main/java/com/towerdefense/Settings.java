package com.towerdefense;

/*
 * store global settings
 */
public class Settings {

    public static String TITLE = "Tower Defense";
    
    public static double SCENE_WIDTH = 640;
    public static double SCENE_HEIGHT = 640;

    public static double ENEMY_MAX_SPEED = 1;
    public static double ENEMY_MAX_FORCE = 0.02;
    public static double ENEMY_SPAWN_TIME = 3;
    public static double ENEMY_SLOW_DOWN_DISTANCE = 32;
    public static int ENEMY_MAX_HEALTH = 10000000;

    public static double WEAPON_MAX_SPEED = 2;
    public static double WEAPON_MAX_FORCE = 0.05;

    public static double BULLET_MAX_SPEED = 2;
    public static double BULLET_SPAWN_TIME = 1;
    public static double BULLET_MAX_DISTANCE = 64 * 3;
    public static int BULLET_Damage = 1;

    //money to buy and upgrade towers
    public static int MONEY = 100;
    public static int TOWER_COST = 0;

    public static double responsiveTileWidth = 64;
}