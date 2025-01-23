package com.example.pongproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenu {

    public static void display(Stage primaryStage) {
        // Hintergrundbild für den Startbildschirm setzen
        Image backgroundImage = new Image(MainMenu.class.getResource("/com/example/pongproject/startscreen.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600);  // Szenegröße anpassen
        backgroundImageView.setFitHeight(400); // Szenegröße anpassen
        backgroundImageView.setPreserveRatio(false);

        // Buttons für das Menü
        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit");

        startButton.setOnAction(e -> PersonalizationScreen.display(primaryStage));
        quitButton.setOnAction(e -> System.exit(0));

        // Layout für die Buttons
        VBox buttonLayout = new VBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(startButton, quitButton);

        // StackPane für das Hauptlayout
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, buttonLayout);

        // Hauptmenü Szene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
