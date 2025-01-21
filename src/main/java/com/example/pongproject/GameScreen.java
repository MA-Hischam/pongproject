package com.example.pongproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.paint.Color;


public class GameScreen {
    private static Ball ball;
    private static Paddle leftPaddle;
    private static Paddle rightPaddle;
    private static int leftScore = 0;
    private static int rightScore = 0;
    private static Stage gameStage;
    private static final double PADDLE_SPEED = 6;
    private static final double PADDLE_LIMIT = 500;
    private static Set<KeyCode> activeKeys = new HashSet<>();


    private static void resetScore() {
        leftScore = 0;
        rightScore = 0;
    }

    public static void display(Stage primaryStage) {
        resetScore();
        gameStage = primaryStage;
        ball = new Ball(300, 250);
        leftPaddle = new Paddle(10, 200, 15, 100);
        rightPaddle = new Paddle(575, 200, 15, 100);

        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), 600, 500);
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));

        scene.setFill(Color.LIGHTBLUE);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                update();
                render(gc);
            }
        }.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong Game");
        primaryStage.show();
    }

    private static void update() {
        if (activeKeys.contains(KeyCode.W) && leftPaddle.getY() > 0) {
            leftPaddle.move(-PADDLE_SPEED);
        }
        if (activeKeys.contains(KeyCode.S) && leftPaddle.getY() < PADDLE_LIMIT - leftPaddle.getHeight()) {
            leftPaddle.move(PADDLE_SPEED);
        }
        if (activeKeys.contains(KeyCode.UP) && rightPaddle.getY() > 0) {
            rightPaddle.move(-PADDLE_SPEED);
        }
        if (activeKeys.contains(KeyCode.DOWN) && rightPaddle.getY() < PADDLE_LIMIT - rightPaddle.getHeight()) {
            rightPaddle.move(PADDLE_SPEED);
        }

        ball.move();

        if (ball.collidesWith(leftPaddle) || ball.collidesWith(rightPaddle)) {
            ball.reverseXDirection();
        }

        if (ball.getX() <= 0) {
            rightScore++;
            ball.resetPosition(300, 250);
        } else if (ball.getX() >= 600) {
            leftScore++;
            ball.resetPosition(300, 250);
        }

        if (leftScore == 10 || rightScore == 10) {
            EndScreen.display(gameStage, leftScore == 10 ? "Left Player" : "Right Player");
        }
    }

    private static void render(GraphicsContext gc) {
        // Hintergrundfarbe (hellblau)
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 600, 500);

        // Zeichne den Ball und die Paddles
        gc.setFill(Color.WHITE); // Dunkelblauer Ball und Paddles
        gc.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        gc.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        gc.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());

        // Zeige den Punktestand an (Textfarbe dunkelblau)
        gc.setFill(Color.WHITE);
        gc.fillText("Left: " + leftScore, 50, 30);
        gc.fillText("Right: " + rightScore, 500, 30);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    }

}

