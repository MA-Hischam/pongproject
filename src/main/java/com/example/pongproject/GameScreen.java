package com.example.pongproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class GameScreen {
    private static Ball ball;
    private static Paddle leftPaddle;
    private static Paddle rightPaddle;
    private static int leftScore;
    private static int rightScore;
    private static Stage primaryStage;
    private static String leftName;
    private static String rightName;
    private static final double PADDLE_SPEED = 4;
    private static final double PADDLE_LIMIT = 500;
    private static Set<KeyCode> activeKeys = new HashSet<>();

    public static void setLeftName(String leftName) {
        GameScreen.leftName = leftName;
    }
    public static void setRightName(String rightName) {
        GameScreen.rightName = rightName;
    }

    public static void display(Stage primaryStage) {
        ball = new Ball(300, 250);
        leftPaddle = new Paddle(10, 250, 10, 100);
        rightPaddle = new Paddle(580, 250, 10, 100);
        leftScore = 0;
        rightScore = 0;

        GameScreen.primaryStage = primaryStage;

        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image backgroundImage = new Image(GameScreen.class.getResource("/com/example/pongproject/GameScreen.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(500);
        backgroundImageView.setPreserveRatio(false);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, canvas);

        Scene scene = new Scene(root, 600, 500);
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

        if (ball.collidesWith(leftPaddle)) {
            ball.reverseXDirection();
        }
        if (ball.collidesWith(rightPaddle)) {
            ball.reverseXDirection();
        }

        if (ball.getX() <= 0) {
            rightScore++;
            ball.resetPosition(295, 250);
        } else if (ball.getX() >= 600) {
            leftScore++;
            ball.resetPosition(295, 250);
        }

        if (leftScore == 10) {
            EndScreen.display(primaryStage, "Left Player");
        } else if (rightScore == 10) {
            EndScreen.display(primaryStage, "Right Player");
        }

        checkPaddleBounds(leftPaddle);
        checkPaddleBounds(rightPaddle);
    }

    private static void checkPaddleBounds(Paddle paddle) {
        double minY = 0;
        double maxY = 500 - paddle.getHeight();

        if (paddle.getY() < minY) {
            paddle.move(minY - paddle.getY());
        } else if (paddle.getY() > maxY) {
            paddle.move(maxY - paddle.getY());
        }
    }

    private static void render(GraphicsContext gc) {
        Image backgroundImage = new Image(GameScreen.class.getResource("/com/example/pongproject/GameScreen.png").toString());
        gc.drawImage(backgroundImage, 0, 0, 600, 500);

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        gc.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        gc.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());

        gc.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 25));

        gc.fillText(leftName + ": " + leftScore, 50, 30);
        gc.fillText(rightName + ": " + rightScore, 430, 30);
    }
}