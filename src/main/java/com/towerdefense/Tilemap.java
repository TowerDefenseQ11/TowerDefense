package com.towerdefense;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;

import java.lang.Math;

import java.io.IOException;

import javafx.geometry.Pos;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/*
 * create tilemap background with correct borders of path
 */
public class Tilemap {

    private Tile[] tiles;
    private int size = 10;
    private int[][] world = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
        {1, 1, 1, 1, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    private TilePane tilePane;


    public Tilemap(Pane layerPane){

        initTilemap();
        drawMap(layerPane);
       
        // Set up the event handlers
        layerPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Check if the player has enough resources to place a tower
                if (Settings.MONEY >= Settings.TOWER_COST) {
                    // Create a new tower and deduct the cost from the player's resources
                    //Tower tower = new Tower((int) event.getX(), (int) event.getY());
                    int x = (int) Math.floor(event.getX() / 64.0) * 64;
                    int y = (int) Math.floor(event.getY() / 64.0) * 64;
                    
                    Weapon weapon = new Weapon(x, y, (Layer) layerPane.getChildren().get(1));
                    
                    Settings.MONEY -= Settings.TOWER_COST;
                }
            }
        });
       
       
    }

    private void initTilemap(){
        int size = 15;
        tiles = new Tile[size];

        try {
            for(int i=0; i<size; i++){
                String name = "";
                switch(i){
                    case 0: {
                        name = "a_empty";
                        break;
                    }
                    case 1: {
                        name = "a_path";
                        break;
                    }
                    case 2: {
                        name = "b_bottom";
                        break;
                    }
                    case 3: {
                        name = "b_left";
                        break;
                    }
                    case 4: {
                        name = "b_right";
                        break;
                    }
                    case 5: {
                        name = "b_top";
                        break;
                    }
                    case 6: {
                        name = "c_bottom_left";
                        break;
                    }
                    case 7: {
                        name = "c_bottom_right";
                        break;
                    }
                    case 8: {
                        name = "c_top_left";
                        break;
                    }
                    case 9: {
                        name = "c_top_right";
                        break;
                    }
                    case 10: {
                        name = "d_bottom_left";
                        break;
                    }
                    case 11: {
                        name = "d_bottom_right";
                        break;
                    }
                    case 12: {
                        name = "d_left_top";
                        break;
                    }
                    case 13: {
                        name = "d_right_top";
                        break;
                    }


                    case 14: { //not used yet
                        name = "a_empty";
                        break;
                    }
                    
                    
                }

                tiles[i] = new Tile();
                //System.out.println(name);
                tiles[i].image = new Image(name+".png");
            }
            

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void drawMap(Pane layerPane){
        tilePane = new TilePane();
        layerPane.getChildren().addAll(tilePane);
        


        tilePane.setPrefColumns(size);
        tilePane.setPrefRows(size);

        updateMapBorders();

        

        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                ImageView img = getTile(world[y][x]);
                tilePane.getChildren().add(img);
                img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Tile pressed ");
                        event.consume();
                    }
               });
            }
        }

        tilePane.setTileAlignment(Pos.TOP_LEFT);
        tilePane.setHgap(0);
        tilePane.setVgap(0);

    }

    private ImageView getTile(int index){
        return new ImageView(tiles[index].image);
    }

    private void updateMapBorders(){
        if(tilePane == null){
            return;
        }

        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                
                if(world[y][x] == 1){ //find each path cell
                    //set neighbours

                    int left = 0;
                    int right = 0;
                    int bottom = 0;
                    int top = 0;

                    if(x > 0){
                        left = world[y][x-1];
                        if(left == 0)
                            world[y][x-1] = 3;
                    }
                    

                    if(x < size-1){
                        right = world[y][x+1];
                        if(right == 0)
                            world[y][x+1] = 4;
                    }
                    

                    if(y > 0){
                        top = world[y-1][x];
                        if(top == 0)
                            world[y-1][x] = 5;
                    }
                    

                    if(y < size-1){
                        bottom = world[y+1][x];
                        if(bottom == 0)
                            world[y+1][x] = 2;
                    }

                    if(left==1 && top==1){
                        //corner bottom right 7
                        world[y+1][x+1] = 7;
                        //corner top left 13
                        world[y-1][x-1] = 13;
                    }

                    if(right==1 && top==1){
                        //corner bottom left
                        world[y+1][x-1] = 6;
                        //corner top right 12
                        world[y-1][x+1] = 12;
                    }

                    if(left==1 && bottom==1){
                        //corner top right
                        world[y-1][x+1] = 9;
                        //corner bottom left 10
                        world[y+1][x-1] = 10;
                    }

                    if(right==1 && bottom==1){
                        //corner top left
                        world[y-1][x-1] = 8;
                        //corner bottom right 11
                        world[y+1][x+1] = 11;
                    }
                }

            }
        }
    }
}
