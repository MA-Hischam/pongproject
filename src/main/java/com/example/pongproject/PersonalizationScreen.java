package com.example.pongproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonalizationScreen {

    public static void display(Stage primaryStage) {
        // Layout für das personalization_screen
        StackPane layout = new StackPane();

         //Hintergrundbild für den Startbildschirm setzen
        Image backgroundImage = new Image(MainMenu.class.getResource("/com/example/pongproject/PersonalizationScreen.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600); // Passe die Größe an die Szene an
        backgroundImageView.setFitHeight(400); // Passe die Größe an die Szene an
        backgroundImageView.setPreserveRatio(true);

        //Name eingeben
        TextField leftName = new TextField();
        TextField rightName = new TextField();

        leftName.setPromptText("Left player name");
        rightName.setPromptText("right player name");

        VBox leftNameVBox = new VBox();
        leftNameVBox.setAlignment(Pos.TOP_LEFT);
        leftNameVBox.getChildren().add(leftName);

        VBox rightNameVBox = new VBox();
        rightNameVBox.setAlignment(Pos.BOTTOM_LEFT);
        rightNameVBox.getChildren().add(rightName);



        // Buttons erstellen
        Button saveAndStartButton = new Button("Save & Start Game");
        saveAndStartButton.setOnAction(e -> {
            // Save the input and switch to the next scene
            GameScreen.setLeftName(leftName.getText());
            GameScreen.setRightName(rightName.getText());
            GameScreen.display(primaryStage);
        });


        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));  // Beendet das Spiel

        // VBox für die Buttons
        VBox buttonLayout = new VBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(saveAndStartButton, quitButton);

        // Füge das Hintergrundbild, die Buttons und die Texteingaben in das StackPane ein
        layout.getChildren().addAll(backgroundImageView, leftNameVBox, rightNameVBox, buttonLayout);

        // Hauptmenü Szene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
