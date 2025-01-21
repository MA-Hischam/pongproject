package com.example.pongproject;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenu {

    public static void display(Stage primaryStage) {
        // Layout für das Hauptmenü
        VBox layout = new VBox(20);
        layout.setAlignment(javafx.geometry.Pos.CENTER); // Center align the children

        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit");

        startButton.setOnAction(e -> GameScreen.display(primaryStage));
        quitButton.setOnAction(e -> System.exit(0));  // Beendet das Spiel

        layout.getChildren().addAll(startButton, quitButton);

        // Hauptmenü Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
