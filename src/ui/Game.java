package ui;

import java.util.Random;

import model.BallsList;
import model.BallTypes.*;
import ui.tabs.LabelPanel;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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

        main.setCenter(game.getBallGame());

        LabelPanel labelPanel = new LabelPanel(game.getBallList().get(0), game.getBallList().get(1));
        SplitPane label = labelPanel.getLabelPanel();

        main.setBottom(label);

        main.setStyle("-fx-background-color: lightblue;");
        

        Scene scene = new Scene(main, gamePanelWidth, gamePanelHeight + labelPaneHeight);

        primaryStage.setTitle("Ball Game");
        primaryStage.setScene(scene);
        game.startGame();
        primaryStage.show(); 
    }

    // MODIFIES: BallsList, BallGame, this
    // EFFECTS: adds balls to the BallsList to be displayed in the ui
    public void setBalls(BallsList lob) {
        //lob.addBall(new FibonacciBall(250, 499));
        lob.addBall(new FibonacciBall(900, 400));
        lob.addBall(new TargetDummy(500, 500, 20000));
    }


}
