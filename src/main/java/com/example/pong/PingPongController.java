package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PingPongController {

    // Variables para los elementos gráficos de la interfaz
    @FXML
    private Pane pane;
    @FXML
    private Rectangle player1;
    @FXML
    private Rectangle player2;
    @FXML
    private Circle ball;

    // Variables para controlar la lógica del juego
    private double player1Y = 250;
    private double player2Y = 250;
    private double ballX = 400;
    private double ballY = 300;
    private double ballSpeedX = 5;
    private double ballSpeedY = 5;

    @FXML
    public void initialize() {
        // Establecer el tamaño y el color de fondo del Pane
        pane.setPrefSize(800, 600);
        pane.setStyle("-fx-background-color: black;");

        // Establecer el tamaño y el color de los rectángulos de los jugadores
        player1.setWidth(20);
        player1.setHeight(100);
        player1.setFill(Color.WHITE);
        player2.setWidth(20);
        player2.setHeight(100);
        player2.setFill(Color.WHITE);

        // Establecer el tamaño y el color de la pelota
        ball.setRadius(20);
        ball.setFill(Color.WHITE);

        // Mover la pelota y los jugadores en un bucle infinito
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBall();
                movePlayer1();
                movePlayer2();
                checkCollision();
            }
        };
        timer.start();

        // Detectar las teclas presionadas para mover el jugador 1
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    player1Y -= 10;
                    break;
                case S:
                    player1Y += 10;
                    break;
            }
        });

        // Detectar las teclas presionadas para mover el jugador 2
        pane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    player2Y -= 10;
                    break;
                case DOWN:
                    player2Y += 10;
                    break;
            }
        });

        // Establecer el Pane como el elemento que detecta las teclas presionadas
        pane.requestFocus();
    }

    private void moveBall() {
        // Mover la pelota en la dirección actual
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Actualizar la posición de la pelota en la interfaz gráfica
        ball.setCenterX(ballX);
        ball.setCenterY(ballY);
    }

    private void movePlayer1() {
        // Actualizar la posición del jugador 1 en la interfaz gráfica
        player1.setY(player1Y);
        }
    private void movePlayer2() {
        // Actualizar la posición del jugador 2 en la interfaz gráfica
        player2.setY(player2Y);
    }

    private void checkCollision() {
        // Revisar si la pelota colisionó con los bordes superior e inferior del Pane
        if (ballY <= ball.getRadius() || ballY >= pane.getHeight() - ball.getRadius()) {
            ballSpeedY *= -1;
        }

        // Revisar si la pelota colisionó con los jugadores
        if (ballX <= player1.getWidth() + ball.getRadius() &&
                ballY >= player1.getY() &&
                ballY <= player1.getY() + player1.getHeight()) {
            ballSpeedX *= -1;
        }
        if (ballX >= pane.getWidth() - player2.getWidth() - ball.getRadius() &&
                ballY >= player2.getY() &&
                ballY <= player2.getY() + player2.getHeight()) {
            ballSpeedX *= -1;
        }
    }}