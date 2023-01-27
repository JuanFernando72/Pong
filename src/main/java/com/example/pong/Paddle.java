package com.example.pong;

import javafx.scene.shape.Rectangle;
public class Paddle extends Rectangle {
    private double x;
    private double y;
    private double width;
    private double height;
    private double velocity;

    public Paddle(double x, double y, double width, double height, double velocity) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }

    public void moveUp() {
        y -= velocity;
        setY(y);
    }

    public void moveDown() {
        y += velocity;
        setY(y);
    }
}