package com.example.pong;


public class Ball {
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;

    public Ball(double x, double y, double xSpeed, double ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }
    //getters y setters
}