package com.towerdefense.engine;

import com.towerdefense.Settings;

import com.towerdefense.waves.handler.WaveHandler;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.layout.Pane;

public class GameGUI extends GUI {

    private HealthBar healthbar;
    private Text money;
    private Game game;

    public GameGUI() {
        super();
    }

    void drawGui() {
        game = new Game(this.getLayer());

        Layer topLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        topLayer.setPickOnBounds(false);

        var background = new Rectangle(0, 0, 250, 64);
        background.setFill(Color.WHITE);

        healthbar = new HealthBar(topLayer);
        healthbar.setStartHealth(5);

        /*
         * create money label
         */
        money = new Text("Money: " + game.getMoney());
        money.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        money.setTranslateX(64+32);
        money.setTranslateY(35+16+8);

        var quitButton = createButton(
            "/GUI/gameGUI/quit.png", 
            Settings.SCENE_WIDTH-80,
            0,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    game.quit();
                    game = null;
                    GuiHandler.switchGui(new EndGUI());
                }
            }
        );

        Image quitImg = new Image(
            this.getClass().getResourceAsStream("/GUI/gameGUI/quit.png"), 
            64, 64, false, false
        );
        Image playImg = new Image(
            this.getClass().getResourceAsStream("/GUI/gameGUI/play.png"), 
            64, 64, false, false
        );

        ImageView pauseButton = new ImageView(quitImg);
        pauseButton.setFitWidth(Settings.getResponsiveTileWidth());
        pauseButton.setFitHeight(Settings.getResponsiveTileWidth());
        pauseButton.setTranslateX(Settings.SCENE_WIDTH-160);
        pauseButton.setTranslateY(0);
        pauseButton.addEventHandler(
            MouseEvent.MOUSE_CLICKED, 
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    boolean isPlaying = game.pauseOrPlayButtonClicked();
                    if(isPlaying){
                        pauseButton.setImage(quitImg);
                    }else{
                        pauseButton.setImage(playImg);
                    }
                }
            }
        );
        
        /*
         * add objects to layer
         */
        this.getLayer().getChildren().addAll(background, topLayer, money, quitButton, pauseButton);
    }

    public HealthBar getHealthBar() {
        return healthbar;
    }

    public void updateMoney() {
        money.setText("Money: " + game.getMoney());
    }

    private ImageView createButton(String imgPath, int x, int y, EventHandler<MouseEvent> event){
        Image img = new Image(
            this.getClass().getResourceAsStream(imgPath), 
            64, 64, false, false
        );
        ImageView button = new ImageView(img);
        button.setFitWidth(Settings.getResponsiveTileWidth());
        button.setFitHeight(Settings.getResponsiveTileWidth());
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event);

        return button;

    } 
}