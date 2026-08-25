package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import ui.BallGame;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import model.Ball;
import model.BallsList;
import model.BallTypes.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        BallGame game = new BallGame(primaryStage);
        BallsList lob = game.getBallList();
        Random randomGen = new Random();

        // for (int i = 0; i < 10; i++) {
        //     double radius = randomGen.nextDouble(100);
        //     double x = randomGen.nextDouble(1000);
        //     double y = randomGen.nextDouble(1000);
        //     double dx = randomGen.nextDouble(15);
        //     double dy = randomGen.nextDouble(15);
        //     int redColorChannel = randomGen.nextInt(256);
        //     int greenColorChannel = randomGen.nextInt(256);
        //     int blueColorChannel = randomGen.nextInt(256);
        //     lob.addBall(new Standard(x, y));
        // }

        lob.addBall(new FibonacciBall(250, 499));
        lob.addBall(new TargetDummy(500, 500, 20000));

        game.startGame();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
