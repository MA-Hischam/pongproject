package com.example.pongproject;

public class Ball {
    private double x, y;
    private double radius;
    private double speedX, speedY;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = 10;
        this.speedX = 3;
        this.speedY = 3;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void reverseXDirection() {
        speedX = -speedX;
    }

    public void reverseYDirection() {
        speedY = -speedY;
    }

    public void increaseSpeed() {
        speedX *= 1.05;
        speedY *= 1.05;
    }

    public void resetPosition(double startX, double startY) {
        this.x = startX;
        this.y = startY;
    }

    public void resetSpeed() {
        this.speedX = 3;
        this.speedY = 3;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public boolean collidesWith(Paddle paddle) {
        return (x >= paddle.getX() && x <= paddle.getX() + paddle.getWidth() &&
                y >= paddle.getY() && y <= paddle.getY() + paddle.getHeight());
    }
}
