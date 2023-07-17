package com.towerdefense.engine;

import com.towerdefense.Settings;

import com.towerdefense.waves.handler.WaveHandler;
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

        healthbar = new HealthBar(topLayer);
        healthbar.setStartHealth(5);

        /*
        create money label
        */
        money = new Text("Money: " + Settings.MONEY);
        money.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        money.setTranslateX(Settings.SCENE_WIDTH - 100);
        money.setTranslateY(35);


        WaveHandler waveHandler = new WaveHandler();

        this.getLayer().getChildren().addAll(topLayer, money);

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