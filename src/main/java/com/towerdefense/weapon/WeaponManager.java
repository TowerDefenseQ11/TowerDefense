package com.towerdefense.weapon;

import com.towerdefense.engine.Layer;
import com.towerdefense.weapon.Weapon;

import java.util.List;
import java.util.ArrayList;

/*
 * handle all weapons and rotate smoothly
 */
public class WeaponManager {
    private Layer playfield;
    private List<Weapon> allWeapons = new ArrayList<>();

    public WeaponManager(Layer playfield){
        this.playfield = playfield;
    }

    public void addWeapon(int x, int y) {
        // create sprite and add to layer
        x *= 64;
        y *= 64;
        Weapon weapon = new Weapon(x, y, playfield);

        // register vehicle
        allWeapons.add(weapon);
    }

    public Weapon getWeapon(int index){
        return allWeapons.get(index);
    }
    public List<Weapon> getAllWeapons(){
        return allWeapons;
    }
}
