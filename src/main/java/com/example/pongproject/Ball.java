package com.example.pongproject;

public class Ball {
    private double x, y, speedX = 3, speedY = 3;
    private final double radius = 15;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += speedX;
        y += speedY;
        if (y <= 0 || y >= 490) reverseYDirection();
    }

    public void reverseXDirection() { speedX = -speedX; }
    public void reverseYDirection() { speedY = -speedY; }
    public void resetPosition(double startX, double startY) { x = startX; y = startY; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }

    public boolean collidesWith(Paddle paddle) {
        // Prüfen, ob der Ball mit dem Paddle kollidiert, wobei der Ballradius berücksichtigt wird
        return (x + radius > paddle.getX() && x < paddle.getX() + paddle.getWidth() &&
                y + radius > paddle.getY() && y < paddle.getY() + paddle.getHeight());
    }

}
