package ui;

import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import model.BallsList;
import model.BallTypes.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

// represents the actual game including labels
public class Game extends Application {
    public static final int gamePanelWidth = 1400;
    public static final int gamePanelHeight = 1000;
    public static final int labelPaneHeight = 200;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane main = new BorderPane();

        BallGame game = new BallGame();
        this.setBalls(game.getBallList());
        game.getBallGame().setPrefSize(gamePanelWidth, gamePanelHeight);
        Pane label = this.getLabels();

        main.setCenter(game.getBallGame());
        main.setBottom(label);

        main.setStyle("-fx-background-color: lightblue;");
        label.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(main, gamePanelWidth, gamePanelHeight + labelPaneHeight);

        primaryStage.setTitle("Ball Game");
        primaryStage.setScene(scene);

        long startTime = System.nanoTime();
        
        
        // long checkpoint3 = System.nanoTime();
        // long end = System.nanoTime();
        // System.out.println("Checkpoint 3: " + (end - checkpoint3));

        game.startGame();
        long checkpoint1 = System.nanoTime();
        System.out.println("CheckPoint1: " + (checkpoint1 - startTime));
        primaryStage.show();  
        
        long checkpoint2 = System.nanoTime();
        System.out.println("CHeckpoint2: " + (checkpoint2 - checkpoint1));
        
        
    }

    // EFFECTS: creates a panel to display information about the game
    public Pane getLabels() {
        Pane labels = new Pane();
        labels.setPrefSize(gamePanelWidth, labelPaneHeight);
        
        return labels;
    }

    // MODIFIES: BallsList, BallGame, this
    // EFFECTS: adds balls to the BallsList to be displayed in the ui
    public void setBalls(BallsList lob) {
        //lob.addBall(new FibonacciBall(250, 499));
        lob.addBall(new FibonacciBall(900, 400));
        lob.addBall(new TargetDummy(500, 500, 20000));
    }


}
