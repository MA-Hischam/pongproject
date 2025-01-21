package com.example.pongproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class PongGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pong Game");
        MainMenu.display(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
