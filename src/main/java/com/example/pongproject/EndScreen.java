package com.example.pongproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

        // Set background image based on the winner
        Image backgroundImage;
        if (winner.equals("Left Player")) {
            backgroundImage = new Image(EndScreen.class.getResource("/com/example/pongproject/p1win.png").toString());

        } else if (winner.equals("Right Player")) {
            backgroundImage = new Image(EndScreen.class.getResource("/com/example/pongproject/p2win.png").toString());

        } else {
            System.out.println("Error: Unknown winner.");
            return;
        }

        if (backgroundImage == null) {
            System.out.println("Error: Background image is null.");
            return;
        }

        ImageView backgroundImageView = new ImageView(backgroundImage);
        layout.getChildren().add(0, backgroundImageView); // Add the background image first

        // Display winner text
        Text winnerText = new Text(winner + " Wins!");
        layout.getChildren().add(winnerText);

        // Back to Main Menu button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> MainMenu.display(primaryStage));  // Zurück zum Hauptmenü
        layout.getChildren().add(backButton);

        // Endbildschirm Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();  // Ensure the stage is shown
    }
}
