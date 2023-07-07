package com.towerdefense.engine;
import javafx.scene.layout.Pane;

import com.towerdefense.map.Map;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class GUI{
    private Layer layer;

    public GUI(){

    
    }


    public void setLayer(Layer layer){
        this.layer = layer;
        drawGui();
    }

    public Layer getLayer(){
        return layer;
    }

    abstract void drawGui();

}