package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.weapon.type.TowerType;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
    private ImageView middleView;
    private Game game;

    public CreateWeaponPopup(int x, int y, Layer layer, Game game){
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.game = game;

        setupImage("towerPopup", middleView, -1, -1);
        setupImage("towerSelectionTop", topView, -1, -1, TowerType.TOWER_1);
        setupImage("towerSelectionRight", rightView, -1, -1, TowerType.TOWER_2);
        setupImage("towerSelectionBottom", bottomView, -1, -1, TowerType.TOWER_1);
        //setupImage("createWeapon", leftView, -1, 0, TowerType.TOWER_2);

        
    }

    private ImageView createImage(String name, ImageView imgView, int offsetX, int offsetY, int sizeFaktor){
        String path = "/GUI/gameGUI/towerPopup/"+name+".png";
        Image img = new Image(
            this.getClass().getResourceAsStream(path), 
            64*sizeFaktor, 64*sizeFaktor, false, false
        );

        imgView = new ImageView(img);

        imgView.setFitWidth(Settings.getResponsiveTileWidth()*sizeFaktor);
        imgView.setFitHeight(Settings.getResponsiveTileWidth()*sizeFaktor);
        imgView.relocate(
            x*Settings.getResponsiveTileWidth() + offsetX*Settings.getResponsiveTileWidth(), 
            y*Settings.getResponsiveTileWidth() + offsetY*Settings.getResponsiveTileWidth()
        );

        layer.getChildren().add(imgView);

        return imgView;
    }

    private void setupImage(String name, ImageView imgView, int offsetX, int offsetY){
        imgView = createImage(name, imgView, offsetX, offsetY, 3);
        imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                game.hideTowerPopup();
                event.consume();
            }
        });
    }

    private void setupImage(String name, ImageView imgView, int offsetX, int offsetY, TowerType towerType){
        imgView = createImage(name, imgView, offsetX, offsetY, 3);
        imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createWeapon(towerType);
                event.consume();
            }
        });

        var moneyLabel = new Text(""+towerType.getMoney());
        moneyLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 8));
        moneyLabel.setTranslateX(
            x*Settings.getResponsiveTileWidth() + offsetX*Settings.getResponsiveTileWidth() 
            + 32 - 4
        );
        moneyLabel.setTranslateY(
            y*Settings.getResponsiveTileWidth() + offsetY*Settings.getResponsiveTileWidth()
            + 64 - 2
        );
        moneyLabel.setFill(Color.WHITE);
        layer.getChildren().add(moneyLabel);
    }

    private void createWeapon(TowerType towerType){
        game.createWeapon(this.x, this.y, towerType);
    }
}
