package model;

import java.util.List;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a list of balls with a dedicated pane for each list
public class BallsList implements Iterable<Ball> {
    private List<Ball> balls;
    private Pane scene;

    // EFFECTS: creates a new ballsList
    public BallsList(Pane scene) {
        balls = new ArrayList<>();
        this.scene = scene;
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }


    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    // EFFECTS: adds list of balls to given pane
    public void addBallsToPane() {
        for (Ball ball: balls) {
            scene.getChildren().add(ball.getBall());
        }
    }

    // EFFECTS: updates all balls in the list of balls;
    public void updateBallsList() {
        for (Ball ball: balls) {
            ball.update();
            this.checkCollision();
            ball.checkStatus(scene, this);
        }
    }

    // MODIFIES: Ball
    // EFFECTS: checks for collision between balls and adjusts direction accordingly
    private void checkCollision() {
        for (Ball ball1: balls) {
            for (Ball ball2: balls) {
                if (!ball1.equals(ball2)) { // prevent checking a ball with itself
                    double combinedRadius = ball1.getRadius() + ball2.getRadius();
                    if (Math.abs(ball1.getX() - ball2.getX()) <=  combinedRadius &&  // impossible for balls to collide if x or y distance is further than combined radius
                        Math.abs(ball1.getY() - ball2.getY()) <= combinedRadius) {
                            double nextDistance = ball1.getNextDistance(ball2);
                        
                        if (nextDistance <= combinedRadius) { 
                            ball1.handleCollision(ball2);
                            this.handleGameLogic(ball1, ball2);
  
                        }
                    }
                }
            }
        }
    }

    @Override
    public Iterator<Ball> iterator() {
        return balls.iterator();
    }

    // MODOFIES: ball1, ball2
    // EFFECTS: handles game logic of ball1 and ball2
    private void handleGameLogic(Ball ball1, Ball ball2) {
        ball1.handleDamage(ball2);
        ball2.handleDamage(ball1);
        ball1.scalePower();
        ball2.scalePower();
        System.out.println(ball1.getTag() + " has health remaning: " + ball1.getHealth());
        System.out.println(ball2.getTag() + " has health remaning: " + ball2.getHealth() + "\n");

    }

    // EFFECTS: returns the number of existing balls
    public int size() {
        return balls.size();
    }
}
