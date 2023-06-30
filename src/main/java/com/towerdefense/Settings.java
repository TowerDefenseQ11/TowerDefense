package com.towerdefense;

import javafx.util.Duration;

/*
 * store global settings
 */
public class Settings {

    public static String TITLE = "Tower Defense";
    
    public static double SCENE_WIDTH = 640;
    public static double SCENE_HEIGHT = 640;

    public static double ENEMY_MAX_SPEED = 0.1;
    public static double ENEMY_MAX_FORCE = 0.001;
    public static double ENEMY_SPAWN_TIME = 6;
    public static double ENEMY_SLOW_DOWN_DISTANCE = 32;
    public static int ENEMY_MAX_HEALTH = 100;
    public static final Duration FRAME_DURATION = Duration.millis(50);

    public static double WEAPON_MAX_SPEED = 2;
    public static double WEAPON_MAX_FORCE = 0.05;

    public static double BULLET_MAX_SPEED = 40;
    public static double BULLET_SPAWN_TIME = 0.2;
    public static double BULLET_RANDOM_SPAWN_TIME = 0.1;
    public static double BULLET_MAX_DISTANCE = 64 * 30;
    public static int BULLET_Damage = 1;

    //money to buy and upgrade towers
    public static int MONEY = 30;
    public static int TOWER_COST = 10;

    private static double responsiveTileWidth = 64;

    public static void setResponsiveTileWidth(double width){
        responsiveTileWidth = width;
        ENEMY_SLOW_DOWN_DISTANCE = width / 2;

    }
    public static double getResponsiveTileWidth(){
        return responsiveTileWidth;

    }
}