package model.BallTypes;

import javafx.scene.paint.Color;
import model.Ball;

// Represents a astandard ball
public class Standard extends Ball {
    private final static double damageScale = 1.5;
    private final static double speedScale = 1.15;

    // EFFECTS: creates a new standard ball
    public Standard(double x, double y) {
        super(x, y, 20, 20, 20, 100, Color.GRAY);
        super.damage = 100;
    }

    // MODIFIES: target, this
    // EFFECTS: inflicts damage onto the target and accounts for target's damage onto this
    @Override
    public void handleDamage(Ball target) {
        health -= target.getDamage();
    }

    // MODOFIES: this
    // EFFECTS: scales damage accordingly to this ball
    @Override
    public void scalePower() {
        super.damage = super.damage * damageScale;
        super.dx = super.dx * speedScale;
        super.dy = super.dy * speedScale;
    }

    @Override
    public String getTag() {
        return "Standard";
    }
    
}
