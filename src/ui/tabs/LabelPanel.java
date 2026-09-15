package ui.tabs;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Ball;
import model.ObserverPattern.Observer;
import ui.Game;

// represents the labelPanel underneath the game to display damage and health information of the two balls
public class LabelPanel implements Observer {
    private SplitPane labels;
    private double ball1Health;
    private double ball1Damage;
    private double ball2Health;
    private double ball2Damage;


    public LabelPanel(Ball ball1, Ball ball2) {
        this.ball1Health = ball1.getHealth();
        this.ball1Damage = ball1.getDamage();
        this.ball2Health = ball2.getHealth();
        this.ball2Damage = ball2.getDamage();

        labels = new SplitPane();
        labels.setPrefSize(Game.gamePanelWidth, Game.labelPaneHeight);
        labels.setStyle("-fx-background-color: white;");
        
        Pane Ball1 = new Pane();

        Pane Ball2 = new Pane();

        labels.getItems().addAll(Ball1, Ball2);
        

    }

    public SplitPane getLabelPanel() {
        return labels;
    }

    // MOIFIES: this
    // EFFECTS: updates ball1 and 2 health and damage
    @Override
    public void update(Ball ball1, Ball ball2) {
        this.ball1Health = ball1.getHealth();
        this.ball1Damage = ball1.getDamage();
        this.ball2Health = ball2.getHealth();
        this.ball2Damage = ball2.getDamage();
    }

    private Pane getPane(int health, int damage) {
        Pane infoDisplay = new Pane();
        Text healthLabel = new Text("health);
    }

}
