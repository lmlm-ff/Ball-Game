package ui.tabs;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Loading extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) {
        this.preloaderStage = primaryStage;
        StackPane root = new StackPane(new Label("Loadingballs"));
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}