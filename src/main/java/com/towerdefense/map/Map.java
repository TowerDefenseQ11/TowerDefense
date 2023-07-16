package com.towerdefense.map;

import com.towerdefense.engine.Game;

import java.io.File;
import java.io.IOException;

import com.towerdefense.Settings;
import com.towerdefense.enemy.handler.EnemyHandler;
import com.towerdefense.map.tile.Tile;
import com.towerdefense.weapon.handler.WeaponHandler;

import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;

import javafx.geometry.Pos;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.io.FilenameFilter;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;


/*
 * create tilemap background with correct borders of path
 */
public class Map {

    private Tile[] tiles;
    private int size = 10;
    private int[][] world = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    private TilePane tilePane;
    private Pane layerPane;

    private double tileWidth;
    private double tileHeight;

    private WeaponHandler weaponHandler;
    private EnemyHandler enemyHandler;

    private String mapName = "pixel";

    private Game game;
    private Random random = new Random();

    private List<int[]> mapPosList;


    public Map(Pane layerPane, Game game){
        this.layerPane = layerPane;
        this.game = game;

        generateRandomPath();
        initTilemap();
        drawMap(layerPane);

        
        updateResponsiveSize();

        this.layerPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateResponsiveSize();
        });

        this.layerPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateResponsiveSize();
        });

       
    }

    /*
     * handle click event on map to create new towers ot upgrade existing towers
     */
    private void handleClick(int x, int y){
        //if (Settings.MONEY >= Settings.TOWER_COST) {
            // Create a new tower and deduct the cost from the player's resources
            //Tower tower = new Tower((int) event.getX(), (int) event.getY());
            //int x = (int) Math.round(Math.floor(event.getX() / Settings.getResponsiveTileWidth()) * Settings.getResponsiveTileWidth());
            //int y = (int) Math.round(Math.floor(event.getY() / Settings.getResponsiveTileWidth()) * Settings.getResponsiveTileWidth());

            game.openCreateWeaponPopup(x, y);
            
            //Weapon weapon = new Weapon(x, y, (Layer) layerPane.getChildren().get(1));
        //}

    }
    private void initTilemap(){
        int s = 7;
        tiles = new Tile[s];

        String subfolderPath = "/map";
        try {
            Path resourceDir = Paths.get(this.getClass().getResource(subfolderPath).toURI());

            int[] index = { 0 }; //array to fix error: local variable index defined in an enclosing scope must be final
            // Iterate over all files in the subfolder
            Files.walk(resourceDir, 1)
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparing(Path::getFileName))
                .forEachOrdered(file -> {
                    try {
                        // Load each image file
                        Image image = new Image(file.toUri().toString());
                        tiles[index[0]] = new Tile();
                        tiles[index[0]].image = image;
                        index[0] ++;
                        System.out.println(file.toUri().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            

        }catch (Exception e){}
    }

    private void drawMap(Pane layerPane){
        tilePane = new TilePane();
        layerPane.getChildren().addAll(tilePane);
        tilePane.setPrefColumns(size);
        tilePane.setPrefRows(size);
        updateMapBorders_Simple();

        for(int y=0; y<size; y++){
            final int currentY = y;
            for(int x=0; x<size; x++){
                final int currentX = x;
                int tileIndex = world[y][x];
                ImageView img = getTile(tileIndex);

                if(tileIndex == 0){
                    img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("Tile pressed ");
                            handleClick(currentX, currentY);
                            event.consume();
                        }
                    });
                }


                tilePane.getChildren().add(img);
            }
            
        }

        tilePane.setTileAlignment(Pos.TOP_LEFT);
        tilePane.setHgap(0);
        tilePane.setVgap(0);

    }

    private ImageView getTile(int index){
        return new ImageView(tiles[index].image);
    }

    private void generateRandomPath() {
        int x = 0;
        int maxX = Settings.SCENE_WIDTH / (int) Settings.getResponsiveTileWidth();
        int y = 5; //maxX / 2; //middle
        int lastDir = 0;

        int[] start = {-1, 4};

        mapPosList = new ArrayList<>();
        mapPosList.add(
            start
        );
        

        //path.add(new Point(x, y));
        world[y][x] = 1;

        while (x < maxX - 2) {
            int direction = random.nextInt(3) - 1; // -1, 0, or 1
            for (int i = 0; i < 15; i++) {
                if(direction == 0){
                    direction = random.nextInt(3) - 1;
                }
            }

            System.out.println(lastDir + " -> " + direction);
           
            if(lastDir != 0){ //lastDir=1; dir=-1
                if(direction != lastDir){
                    direction = 0; //smooth curves -> no "grouping" of tiles allowed
                    System.out.println("dir=0");
                }
            }
            
            // Ensure the path stays within bounds
            if (y+direction < 0 || y+direction >= maxX) {
                direction=0;
            } 

            lastDir = direction;
            
            y += direction;
            x += 1; //random.nextInt(2);
            //x++;
            //path.add(new Point(x, y));
            world[y][x] = 1;
            world[y-direction][x] = 1;

            int[] point1 = {x, y-direction};
            mapPosList.add(
                point1
            );
            int[] point2 = {x, y};
            mapPosList.add(
                point2
            );
        
        }
        world[y][x+1] = 1;
        int[] pointEnd = {x+1, y};
        mapPosList.add(
            pointEnd
        );
    }

    private void updateMapBorders_Simple(){
        if(tilePane == null){
            return;
        }

        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                
                if(world[y][x] == 1){ //find each path cell
                    //set neighbours

                    int left = getWorldTile(x-1, y);
                    int right = getWorldTile(x+1, y);
                    int bottom = getWorldTile(x, y+1);
                    int top = getWorldTile(x, y-1);

                    if(left == 0 && right == 0){
                        world[y][x] = 3;//6; //botom top
                    }
                    else if(top == 0 && bottom == 0){
                        world[y][x] = 6;//3; //left right
                    }

                    else if(bottom == 0 && right == 0){
                        world[y][x] = 4; //left top
                    }
                    else if(bottom == 0 && left == 0){
                        world[y][x] = 5; //right top
                    }

                    else if(top == 0 && left == 0){
                        world[y][x] = 2; //bottom right
                    }
                    else if(top == 0 && right == 0){
                        world[y][x] = 1; //bottom left
                    }

                }

            }
        }
        
        updateBackgroundTiles_Simple();
    }

    /*
     * returns tile index of given position
     */
    private int getWorldTile(int x, int y){
        int tile = 0;
        if( 
            x >= 0 &&
            x < size &&
            y >= 0 &&
            y < size

        ){
            tile = world[y][x];
        }
        
        return tile;
    }

    private void updateBackgroundTiles_Simple(){
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                if(world[y][x] == 0){
                    world[y][x] = 0;
                }
            }
        }
    }

    private void updateResponsiveSize(){
        System.out.println("updateResponsiveSize");
        double availableWidth = layerPane.getWidth();
        double availableHeight = layerPane.getHeight();
        int numColumns = tilePane.getPrefColumns();
        int numRows = tilePane.getPrefRows();
        tileWidth = availableWidth / numColumns;
        tileHeight = availableHeight / numRows;

        if(tileWidth > tileHeight){
            tileWidth = tileHeight;
        }
        else if(tileHeight > tileWidth){
            tileHeight = tileWidth;
        }
        Settings.setResponsiveTileWidth(tileWidth);

        tilePane.getChildren().forEach(tile -> {
            ImageView imageView = (ImageView) tile; // Annahme: Die Tiles sind vom Typ ImageView
            imageView.setFitWidth(tileWidth);
            imageView.setFitHeight(tileHeight);
        });

        if(weaponHandler != null){
            weaponHandler.updateResponsiveSize();
        }
        if(enemyHandler != null){
            enemyHandler.updateResponsiveSize();
        }
    }

    public void setWeaponHandler(WeaponHandler weaponHandler){
        this.weaponHandler = weaponHandler;
    }
    public void setEnemyHandler(EnemyHandler enemyHandler){
        this.enemyHandler = enemyHandler;
    }

    public List<int[]> getMapPosList(){
        return mapPosList;
    }
}
