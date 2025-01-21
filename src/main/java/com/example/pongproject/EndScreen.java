package com.example.pongproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class EndScreen {

    public static void display(Stage primaryStage, String winner) {
        if (primaryStage == null) {
            System.out.println("Error: primaryStage is null.");
            return;
        }

        // Layout für das Endscreen
        VBox layout = new VBox(20);  // VBox für vertikale Anordnung
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: lightblue;"); // Hintergrundfarbe hellblau

        // Text "Winner" in dunkelblau
        Text winnerText = new Text(winner + " Wins!");
        winnerText.setFill(Color.WHITE);

        // Padding für Text nach oben anpassen
        winnerText.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Setzt die Schrift auf Arial, fett und Größe 50
        layout.getChildren().add(winnerText);

        // Button für Zurück zum Hauptmenü
        Button backButton = new Button("Back to Main Menu");
        backButton.setStyle("-fx-text-fill: darkblue;"); // Textfarbe dunkelblau

        // Padding für den Button nach oben verschieben
        backButton.setPadding(new Insets(5, 0, 0, 0)); // Nach oben verschieben
        backButton.setOnAction(e -> MainMenu.display(primaryStage));  // Zurück zum Hauptmenü
        layout.getChildren().add(backButton);

        // Endbildschirm Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
    }
}
