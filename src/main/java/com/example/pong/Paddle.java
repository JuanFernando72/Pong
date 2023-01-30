package com.example.pong;

import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Paddle extends Rectangle {
    private static final int PADDLE_HEIGHT = 100;
    private static final int PADDLE_WIDTH = 20;
    private static final int SPEED = 10;

    public Paddle(double x, double y) {
        super(PADDLE_WIDTH, PADDLE_HEIGHT);
        setTranslateX(x);
        setTranslateY(y);

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    setTranslateY(getTranslateY() - SPEED);
                } else if (event.getCode() == KeyCode.DOWN) {
                    setTranslateY(getTranslateY() + SPEED);
                }
            }
        });
    }
}