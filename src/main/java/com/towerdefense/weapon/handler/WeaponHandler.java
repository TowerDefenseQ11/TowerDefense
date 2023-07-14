package com.towerdefense.weapon.handler;

import com.towerdefense.Settings;
import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.engine.Layer;
import com.towerdefense.weapon.Weapon;
import com.towerdefense.weapon.type.TowerType;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/*
 * handle all weapons and rotate smoothly
 */
public class WeaponHandler {
    private Layer playfield;
    private List<Weapon> allWeapons = new ArrayList<>();
    private EnemyHandler enemyManager;

    public WeaponHandler(Layer playfield, EnemyHandler enemyManager){
        this.playfield = playfield;
        this.enemyManager = enemyManager;
    }

    public void addWeapon(int x, int y, TowerType towerType) {
        // create sprite and add to layer
        //x *= 64;
        //y *= 64;
        x *= Settings.getResponsiveTileWidth();
        y *= Settings.getResponsiveTileWidth();
        Weapon weapon = new Weapon(x, y, playfield, enemyManager, towerType);

        // register vehicle
        allWeapons.add(weapon);
    }

    public Weapon getWeapon(int index){
        return allWeapons.get(index);
    }
    public List<Weapon> getAllWeapons(){
        return allWeapons;
    }

    /*
     * updates size and position of all weapons
     */
    public void updateResponsiveSize(){
        allWeapons.forEach(weapon -> {
            weapon.updateResponsiveSize();
        });
    }
}
