package com.example.pongproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScreen {
    private static Ball ball;
    private static Paddle leftPaddle;
    private static Paddle rightPaddle;
    private static int leftScore;
    private static int rightScore;
    private static Stage primaryStage;
    private static String leftName;
    private static String rightName;

    public static void setLeftName(String leftName) {
        GameScreen.leftName = leftName;
    }
    public static void setRightName(String rightName) {
        GameScreen.rightName = rightName;
    }

    public static void display(Stage primaryStage) {
        // Initialize the ball and paddles
        ball = new Ball(300, 250);
        leftPaddle = new Paddle(10, 250, 10, 100);
        rightPaddle = new Paddle(580, 250, 10, 100);
        leftScore = 0;
        rightScore = 0;

        // Store the primaryStage in a static variable
        GameScreen.primaryStage = primaryStage;

        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new StackPane(canvas), 600, 500);
        scene.setOnKeyPressed(GameScreen::handleKeyPress);



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

    private static void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                rightPaddle.move(-20);
                break;
            case DOWN:
                rightPaddle.move(20);
                break;
            case W:
                leftPaddle.move(-20);
                break;
            case S:
                leftPaddle.move(20);
                break;
        }
    }

    private static void update() {
        ball.move();

        // Ball collision with paddles
        if (ball.collidesWith(leftPaddle)) {
            ball.reverseXDirection();
            //ball.increaseSpeed();
        }
        if (ball.collidesWith(rightPaddle)) {
            ball.reverseXDirection();
            //ball.increaseSpeed();
        }

        // If the ball goes out of bounds, update the score
        if (ball.getX() <= 0) {
            rightScore++; // Right player scores
            ball.resetPosition(300, 250);
            //ball.resetSpeed();
        } else if (ball.getX() >= 600) {
            leftScore++; // Left player scores
            ball.resetPosition(300, 250);
            //ball.resetSpeed();
        }

        // Check if either player reaches 10 points
        if (leftScore == 10) {
            EndScreen.display(primaryStage, "Left Player");
        } else if (rightScore == 10) {
            EndScreen.display(primaryStage, "Right Player");
        }

        // Boundary checks for paddles
        checkPaddleBounds(leftPaddle);
        checkPaddleBounds(rightPaddle);
    }

    private static void checkPaddleBounds(Paddle paddle) {
        double minY = 0;
        double maxY = 500 - paddle.getHeight(); // Adjust for the height of the canvas

        if (paddle.getY() < minY) {
            paddle.move(minY - paddle.getY());
        } else if (paddle.getY() > maxY) {
            paddle.move(maxY - paddle.getY());
        }
    }

    private static void render(GraphicsContext gc) {
        // Dark background color for the game screen
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, 600, 500);

        // Drawing ball and paddles
        gc.setFill(javafx.scene.paint.Color.WHITE); // White color for the ball and paddles
        gc.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        gc.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        gc.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());

        // Display score
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillText(leftName + ": " + leftScore, 50, 30);
        gc.fillText(rightName + ": " + rightScore, 500, 30);
    }
}
