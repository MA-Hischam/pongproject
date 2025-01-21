package com.example.pongproject;

public class Paddle {
    private double x, y;
    private final double width, height;

    public Paddle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(double dy) {
        y = Math.max(0, Math.min(y + dy, 500 - height));
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
