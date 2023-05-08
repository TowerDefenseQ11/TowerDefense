package com.towerdefense;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.canvas.*;
import javafx.scene.image.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.*;
import javafx.scene.control.Button;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;


/*
 * demo to test javafx features
 */
public class Demo {
    public Demo(Stage stage){
        Group root = new Group();
        Scene theScene = new Scene( root );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image earth = new Image( "Lieferroboter.png" );
        gc.drawImage( earth, 200, 200 );
        
        Rectangle r = new Rectangle();
        r.setY(20);
        r.setWidth(100);
        r.setHeight(10);
        root.getChildren().add(r);

        FadeTransition ft = new FadeTransition(Duration.millis(3000), r);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();


        Button btn = new Button();
        //btn.setStyle("-fx-font-size:40");
        btn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 25");
        //btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setText("test");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });



         /* 
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 300, 250));
        */
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);
                gc.drawImage( earth, x, y );
            }
        };




        stage.setScene( theScene );

    }
}
