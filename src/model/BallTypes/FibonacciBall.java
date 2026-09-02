package model.BallTypes;

import javafx.scene.paint.Color;
import model.Ball;

public class FibonacciBall extends Ball {
    private int prev1 = 1;
    private int prev2 = 0;


    // EFFECTS: creates a new standard ball
    public FibonacciBall(double x, double y) {
        super(x, y, 20, 20, 50, 100, Color.BROWN);
        super.damage = 0;
    }

    // MODIFIES: target, this
    // EFFECTS: inflicts damage onto the target and accounts for target's damage onto this
    @Override
    public void handleDamage(Ball target) {
        health -= target.getDamage();
    }

    // MODIFIES: this
    // EFFECTS: scales damage of this ball according to the Fibonacci sequence
    @Override
    public void scalePower() {
        int currentDamage = prev1 + prev2;
        prev2 = prev1;
        prev1 = currentDamage;
        super.damage = currentDamage;

    }

    //EFFECTS: returns tag of this ball
    @Override
    public String getTag() {
        return "Fibonacci";
    }
}
