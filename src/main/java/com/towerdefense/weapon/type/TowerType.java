package com.towerdefense.weapon.type;

public enum TowerType {
    
    TOWER_1("Tower1", 10, "tower_1", 4, 3),
    TOWER_2("Tower2", 20, "tower_2", 6, 5);

    private final String displayName;
    private final int money;
    private final String towerFolder;
    private final int towerImageCount;
    private final int shootFrameIndex;

    TowerType(String displayName, int money, String towerFolder, int towerImageCount, int shootFrameIndex){
        this.displayName = displayName;
        this.money = money;
        this.towerFolder = towerFolder;
        this.towerImageCount = towerImageCount;
        this.shootFrameIndex = shootFrameIndex;
    }

    /*
     * returns folder which contains all sprites for this tower
     */
    public String getTowerFolder(){
        return towerFolder;
    }

    /*
     * returns number of frames
     */
    public int getTowerImageCount(){
        return towerImageCount;
    }

    /*
     * returns the frame index at which a new bullet should be spawned
     */
    public int getShootFrameIndex(){
        return shootFrameIndex;
    }

    public int getMoney(){
        return money;
    }
}
