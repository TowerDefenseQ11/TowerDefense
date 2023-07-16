package com.towerdefense.engine;

import com.towerdefense.Settings;

import javafx.scene.layout.Pane;

public class GuiHandler {
    private static GUI currentGUI;
    private static Pane layerPane;

    public static Pane getLayerPane(){
        return layerPane;
    }

    public static void setLayerPane(Pane pane){
        layerPane = pane;
    }
    
    public static void switchGui(GUI newGUI)
    {
        if(currentGUI != null){
            Layer lastLayer = currentGUI.getLayer();
            layerPane.getChildren().remove(lastLayer);
        }
        currentGUI = null;
        currentGUI = newGUI;

        Layer guiLayer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layerPane.getChildren().addAll(guiLayer);
        newGUI.setLayer(guiLayer);
    }

    public static GUI getGUI(){
        return currentGUI;
    }
}
