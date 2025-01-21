package com.example.pongproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import java.util.HashSet;
import java.util.Set;

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

    public static void display(Stage primaryStage) {
        gameStage = primaryStage;
        ball = new Ball(300, 250);
        leftPaddle = new Paddle(10, 200, 10, 100);
        rightPaddle = new Paddle(580, 200, 10, 100);

        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), 600, 500);
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));

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
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, 600, 500);
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        gc.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        gc.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());
        gc.fillText("Left: " + leftScore, 50, 30);
        gc.fillText("Right: " + rightScore, 500, 30);
    }
}
