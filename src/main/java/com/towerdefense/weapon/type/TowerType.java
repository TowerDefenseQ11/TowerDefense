package com.towerdefense.weapon.type;

public enum TowerType {
    
    TOWER_1("Tower1", 10, "tower_1", 4),
    TOWER_2("Tower2", 20, "tower_2", 6);

    private final String displayName;
    private final double money;
    private final String towerFolder;
    private final int towerImageCount;

    TowerType(String displayName, double money, String towerFolder, int towerImageCount){
        this.displayName = displayName;
        this.money = money;
        this.towerFolder = towerFolder;
        this.towerImageCount = towerImageCount;
    }

    /*
     * returns folder which contains all sprites for this tower
     */
    public String getTowerFolder(){
        return towerFolder;
    }
}
