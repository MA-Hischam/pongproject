package com.example.pongproject;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class MainMenu {

    public static void display(Stage primaryStage) {
        // Layout für das Hauptmenü
        StackPane layout = new StackPane();

        // Hintergrundbild für den Startbildschirm setzen
        Image backgroundImage = new Image(MainMenu.class.getResource("/com/example/pongproject/startscreen.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600); // Passe die Größe an die Szene an
        backgroundImageView.setFitHeight(400); // Passe die Größe an die Szene an
        backgroundImageView.setPreserveRatio(true);

        // Buttons erstellen
        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit");

        //startButton.setOnAction(e -> GameScreen.display(primaryStage));
        startButton.setOnAction(e -> PersonalizationScreen.display(primaryStage));
        quitButton.setOnAction(e -> System.exit(0));  // Beendet das Spiel

        // VBox für die Buttons
        VBox buttonLayout = new VBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(startButton, quitButton);

        // Füge das Hintergrundbild und die Buttons in das StackPane ein
        layout.getChildren().addAll(backgroundImageView, buttonLayout);

        // Hauptmenü Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
