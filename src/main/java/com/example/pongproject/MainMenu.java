package com.example.pongproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu {
    public static void display(Stage primaryStage) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        // Setze die Hintergrundfarbe auf hellblau
        layout.setStyle("-fx-background-color: lightblue;");

        // Text-Label für den Titel, jetzt viel größer und fett
        Text title = new Text("Pong Game");
        title.setFill(Color.WHITE); // Setzt die Farbe des Textes auf schwarz
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // Setzt die Schrift auf Arial, fett und Größe 50

        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit");

        startButton.setStyle("-fx-text-fill: darkblue;");
        quitButton.setStyle("-fx-text-fill: darkblue;");

        startButton.setOnAction(e -> GameScreen.display(primaryStage));
        quitButton.setOnAction(e -> System.exit(0));

        layout.getChildren().addAll(title, startButton, quitButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Pong Game"); // Der Fenstertitel bleibt unverändert
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
