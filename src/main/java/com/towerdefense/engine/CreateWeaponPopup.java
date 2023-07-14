package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/*
 * Creates a popup to select and create diffrent weapons at position (x, y) 
 */
public class CreateWeaponPopup {
    private int x;
    private int y;
    private Layer layer;
    private ImageView backgroundView;
    private ImageView topView;
    private ImageView rightView;
    private ImageView bottomView;
    private ImageView leftView;
    private Game game;

    public CreateWeaponPopup(int x, int y, Layer layer, Game game){
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.game = game;

        //setupImage("createWeapon", backgroundView, 0, 0);

        setupImage("createWeapon", topView, 0, 1);
        setupImage("createWeapon", rightView, 1, 0);
        setupImage("createWeapon", bottomView, 0, -1);
        setupImage("createWeapon", leftView, -1, 0);
        
    }

    private void setupImage(String name, ImageView imgView, int offsetX, int offsetY){
        String path = "/tower_popup/"+name+".png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path), 
            64, 64, false, false
        );

        imgView = new ImageView(img);

        imgView.setFitWidth(Settings.getResponsiveTileWidth());
        imgView.setFitHeight(Settings.getResponsiveTileWidth());
        imgView.relocate(
            x*Settings.getResponsiveTileWidth() + offsetX*Settings.getResponsiveTileWidth(), 
            y*Settings.getResponsiveTileWidth() + offsetY*Settings.getResponsiveTileWidth()
        );

        imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //todo: add weapont type
                createWeapon();
                event.consume();
            }
        });

        layer.getChildren().add(imgView);
    }

    private void createWeapon(){
        System.out.println("create wepon");
        game.createWeapon(this.x, this.y);
    }
}
