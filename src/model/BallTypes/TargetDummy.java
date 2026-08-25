package model.BallTypes;

import javafx.scene.paint.Color;
import model.Ball;

// Represents a target dummy to inflict damage upon
public class TargetDummy extends Ball {

    public TargetDummy(double x, double y, double health) {
        super(x, y, 0, 0, 300, health, Color.BLACK);
    }

    @Override
    public void handleDamage(Ball target) {
        health -= target.getDamage();
    }

    @Override
    public void scalePower() {
    }

    @Override
    public String getTag() {
        return "Dummy";
    }

}
