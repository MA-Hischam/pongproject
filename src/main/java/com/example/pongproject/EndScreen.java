package com.example.pongproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScreen {
    public static void display(Stage primaryStage, String winner) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(new Text(winner + " Wins!"));

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> MainMenu.display(primaryStage));
        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
    }
}
