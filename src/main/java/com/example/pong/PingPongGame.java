package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class PingPongGame extends Application {

    //variable
    private static final int width = 800;
    private static final int height = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double BALL_R = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = height / 2;
    private double playerTwoYPos = height / 2;
    private double ballXPos = width / 2;
    private double ballYPos = height / 2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - PLAYER_WIDTH;
    double playerTwoYDirection = 1;
    double playerOneYDirection= 1;
    double paddleHeight = 1;
    public void start(Stage stage) throws Exception {
        stage.setTitle("PING PONG");
        //background size
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //JavaFX Timeline = free form animation defined by KeyFrames and their duration
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        //number of cycles in animation INDEFINITE = repeat indefinitely
        tl.setCycleCount(Timeline.INDEFINITE);

        //keyboard control (move and space)
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                playerOneYPos -= 10;
            } else if (e.getCode() == KeyCode.S) {
                playerOneYPos += 10;
            } else if (e.getCode() == KeyCode.UP) {
                playerTwoYPos -= 10;
            } else if (e.getCode() == KeyCode.DOWN) {
                playerTwoYPos += 10;
            }

        });
        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                gameStarted = true;
            }
        });
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    private void run(GraphicsContext gc) {
        //set graphics
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if(gameStarted) {
            //set ball movement
            ballXPos+=ballXSpeed;
            ballYPos+=ballYSpeed;

            //draw the ball
            gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);

        } else {
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(Font.font("Papyrus", FontWeight.EXTRA_BOLD, 40));
            gc.strokeText("Espacio para empezar", width / 2, height / 2);

            //reset the ball start position
            ballXPos = width / 2;
            ballYPos = height / 2;

            //reset the ball speed and the direction
            ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
        }

        //makes sure the ball stays in the canvas
        if(ballYPos > height || ballYPos < 0) ballYSpeed *=-1;

        //if you miss the ball, computer gets a point
        if(ballXPos < playerOneXPos - PLAYER_WIDTH) {
            scoreP2++;
            gameStarted = false;
        }

        //if the computer misses the ball, you get a point
        if(ballXPos > playerTwoXPos + PLAYER_WIDTH) {
            scoreP1++;
            gameStarted = false;
        }

        //increase the speed after the ball hits the player
        if( ((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
                ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }
        //Aqui para que no se desborden las paletas
        if (playerOneYPos <= 0) {
            playerOneYPos = 0;
        } else if (playerOneYPos >= height - PLAYER_HEIGHT) {
            playerOneYPos = height - PLAYER_HEIGHT;
        }
        if (playerTwoYPos <= 0) {
            playerTwoYPos = 0;
        } else if (playerTwoYPos >= height - PLAYER_HEIGHT) {
            playerTwoYPos = height - PLAYER_HEIGHT;
        }
        //draw score
        gc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t" + scoreP2, width / 2, 100);
        //draw player 1 & 2
        gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    // start the application
    public static void main(String[] args) {
        launch(args);
    }
}




