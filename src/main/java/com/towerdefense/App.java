package com.towerdefense;

import com.towerdefense.engine.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.layout.Pane;


/**
 * Tower Defense JavaFX application
 */
public class App extends Application {

    private static Scene scene;
    // entire game as layers = root
    private Pane layerPane;


    

    @Override
    public void start(Stage stage) throws IOException {
        
        stage.setTitle(Settings.TITLE);   
        
        layerPane = new Pane();
        scene = new Scene(layerPane, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();

    
         //start & create Game
        //Game game = new Game(layerPane);
        GuiHandler.setLayerPane(layerPane);
        StartGUI startGui = new StartGUI();
        GuiHandler.switchGui(startGui);

    }

    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}