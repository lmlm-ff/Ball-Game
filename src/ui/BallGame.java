package ui;

import model.Ball;
import model.BallsList;

import java.util.List;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

// Represents a ball game
public class BallGame extends AnimationTimer {
    private BallsList balls;
    private Pane pane;
    private Stage primaryStage;

    public static final int width = 1200;
    public static final int height = 1200;

    // EFFECTS: creates a new ball game
    public BallGame(Stage primaryStage) {
        this.primaryStage = primaryStage;

        pane = new Pane();
        this.balls = new BallsList(pane);
        Scene s1 = new Scene(pane, width, height);
        primaryStage.setScene(s1);
    }

    // MODOFIES: this
    // EFFECTS: starts game
    public void startGame() {
        balls.addBallsToPane();
        super.start();
        primaryStage.show();
    }

    // MODIFIES: this
    // EFFECTS: updates every frame that AnimationTimer is active, updates ball's health and speed, handles collision,
    //          removes ball if dead
    @Override
    public void handle(long arg0) {
        balls.updateBallsList();
        this.checkEndGame();

    }

    // EFFECTS: returns ball list
    public BallsList getBallList() {
        return balls;
    }

    // MODIFIES: this
    // EFFECTS: ends game when one ball is remaining, prints stats of each ball
    public void checkEndGame() {
        if (balls.size() <= 1) {
            super.stop();
            primaryStage.hide();
        }
    }
}
