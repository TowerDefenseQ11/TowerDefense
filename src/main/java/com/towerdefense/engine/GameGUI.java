package com.towerdefense.engine;

import com.towerdefense.Settings;
import com.towerdefense.map.Map;
import com.towerdefense.waves.WaveCallback;
import com.towerdefense.waves.handler.WaveHandler;


import com.towerdefense.waves.types.WaveTypes;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.EventListener;

//import javafx.scene.layout.Pane;

public class GameGUI extends GUI {

    private HealthBar healthbar;
    private Text money;
    private Game game;

    public GameGUI() {
        super();
    }

    void drawGui() {
        game = new Game(GuiHandler.getLayerPane());

        Layer topLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        GuiHandler.getLayerPane().getChildren().addAll(topLayer);
        topLayer.setPickOnBounds(false);

        healthbar = new HealthBar(topLayer);
        healthbar.setStartHealth(5);

        //create money label
        money = new Text("Money: " + Settings.MONEY);
        money.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        money.setTranslateX(Settings.SCENE_WIDTH - 100);
        money.setTranslateY(35);
        GuiHandler.getLayerPane().getChildren().addAll(money);


        WaveHandler waveHandler = new WaveHandler();
        waveHandler.changeWave(WaveTypes.WAVE_1, new WaveCallback<Boolean>() {
            @Override
            public void wave(Boolean done) {
                System.out.println("TEXT ANIMATION DEBUG");
                Text waveText = new Text(WaveHandler.CURRENT_WAVE.getName());
                waveText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
                waveText.setTranslateX(Settings.SCENE_WIDTH/2);
                waveText.setTranslateY(Settings.SCENE_WIDTH/2);

                FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), waveText);
                fadeTransition.setFromValue(0.0);
                fadeTransition.setToValue(1.0);
                fadeTransition.setCycleCount(1);
                fadeTransition.setAutoReverse(false);
                fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {

                    }
                });
                fadeTransition.play();
                GuiHandler.getLayerPane().getChildren().addAll(waveText);
            }
        });


        /* 
        //create dropdown menue
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "Option 1",
        "Option 2",
        "End Game"
        );
        final ComboBox dropDownMenue = new ComboBox(options);
        dropDownMenue.setTranslateX(550);
        dropDownMenue.setTranslateY(0);


        dropDownMenue.setOnAction(event -> {
            String selectedOption = comboBox.getSelectionModel().getSelectedItem();
            if (selectedOption.equals("Option 1")) {
                // Aktion für Option 1
                System.out.println("Option 1 ausgewählt");
            } else if (selectedOption.equals("Option 2")) {
                // Aktion für Option 2
                System.out.println("Option 2 ausgewählt");
            } else if (selectedOption.equals("Option 3")) {
                // Aktion für Option 3
                endGUI endGui = new endGUI();
                GuiHandler.switch(endGui);
            }
        });
        GuiHandler.getLayerPane().getChildren().addAll(dropDownMenue);
        */
    }


    public HealthBar getHealthBar() {
        return healthbar;
    }

    public void updateMoney() {
        money.setText("Money: " + Settings.MONEY);
    }
}