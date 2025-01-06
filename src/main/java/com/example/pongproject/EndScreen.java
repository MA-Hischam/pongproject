package com.example.pongproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScreen {

    public static void display(Stage primaryStage, String winner) {
        if (primaryStage == null) {
            System.out.println("Error: primaryStage is null.");
            return;
        }

        // Layout für das Endscreen
        StackPane layout = new StackPane();
        layout.getChildren().add(new Text(winner + " Wins!"));

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> MainMenu.display(primaryStage));  // Zurück zum Hauptmenü
        layout.getChildren().add(backButton);

        // Endbildschirm Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
    }
}
