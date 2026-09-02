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
import ui.Game;

public class Main {
    
    public static void main(String[] args) {
        Application.launch(Game.class, args);
    }
}
