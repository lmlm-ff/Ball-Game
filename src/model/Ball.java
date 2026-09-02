package model;

import ui.BallGame;
import ui.Game;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.List;


// Represents a ball with center x, y, and speed
public abstract class Ball {
    private double x;
    private double y;
    protected double dx;
    protected double dy;
    private double radius;
    private Color color;
    protected double health;
    protected double damage;

    private double mass;
    private StackPane ball;
    private final double massRatio = 0.3;

    // EFFECTS: creates a ball with given radius, x and y coordinate, and speed
    public Ball(double x, double y, double dx, double dy, double radius, double health, Color color) {
        this.setX(x);
        this.setY(y);

        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
        this.mass = radius * massRatio; // mass should directly correlate to radius
        this.health = health;

        ball = new StackPane();
        Circle circle = new Circle(radius);
        circle.setFill(color);
        Text label = new Text(this.getTag());
        label.setFill(Color.WHITE);
        label.setStroke(Color.BLACK);
        label.setStrokeWidth(2);
        label.setStyle("-fx-font-size: 50 px");


        ball.getChildren().addAll(circle, label);

        ball.setLayoutX(x - radius);
        ball.setLayoutY(y - radius);
    }

    // MODOFIES: this
    // EFFECTS: sets x level of ball, adjusts to be within border with 1 unit spacing if ball would be outisde or stuck in border
    private void setX(double x) {
        if (x + radius > Game.gamePanelWidth) {
            this.x = Game.gamePanelWidth - radius - 1;
        } else if (x - radius < 0) {
            this.x = radius + 1;
        } else {
            this.x = x;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets y level of ball, adjusts to be within border with 1 unit spacing if ball would be outside or stuck in border
    private void setY(double y) {
        if (y + radius > Game.gamePanelHeight) {
            this.y = Game.gamePanelHeight - radius - 1;
        } else if (y - radius < 0) {
            this.y = radius + 1;
        } else {
            this.y = y;
        }

    }

    // MODIFIES: this
    // EFFECTS: updates ball's position by adding dy/dx to y/x, checks for boundaries
    //          of window
    public void update() {
        if (!checkVerticalBound()) {

            this.changeDY(- dy);
        } 

        if (!checkHorizontalBound()) {
            this.changeDX(- dx);
        } 

        this.x += dx;
        this.y += dy;

        ball.setLayoutX(x - radius);
        ball.setLayoutY(y - radius);
        
    }

    // EFFECTS: checks whether ball's x coordinate is in boundaries
    private boolean checkHorizontalBound() {
        if (x + radius > Game.gamePanelWidth) {
            return false;
        }

        if (x - radius < 0) {
            return false;
        }

        return true;
    }

    // EFFECTS: checks whether ball's y coordinate is in boundaries
    private boolean checkVerticalBound() {
        if (y + radius > Game.gamePanelHeight) {
            return false;
        }

        if (y - radius < 0) {
            return false;
        }

        return true;
    }

    // MODIFIES: this, ball
    // EFFECTS: switches dx velocity of ball and this
    public void switchDX(Ball ball) {
        double speed = ball.getDX();
        ball.changeDX(this.dx);
        changeDX(speed);
    }

    // MODIFIES: this, ball
    // EFFECTS: switches dy velocity of ball and this
    public void switchDY(Ball ball) {
        double speed = ball.getDY();
        ball.changeDY(this.dy);
        changeDY(speed);
    }

    // MODIFIES: this, other
    // EFFECTS: handles collision between this ball and other ball and positions ball to prevent overlap
    public void handleCollision(Ball other) {
        double x2 = other.getX();
        double y2 = other.getY();
        double dx2 = other.getDX();
        double dy2 = other.getDY();
        double m2 = other.getMass();

        double deltaX = x2 - x;
        double deltaY = y2 - y;
        double distanceSquared = deltaX * deltaX + deltaY*deltaY;

        // Angles
        double deltaDx = dx2 - dx;
        double deltaDy = dy2 - dy;

        double distance = this.getDistance(other);
        double overlap = radius + other.getRadius() - distance;

        // Preventing overlap
        double nx = deltaX / distance;
        double ny = deltaY / distance;

        this.x -= nx * overlap / 2;
        this.y -= ny * overlap / 2;

        other.changeX(x2 + nx * overlap / 2);
        other.changeY(y2 + ny * overlap / 2);

        // Applying collision formula
        double dot = deltaDx * deltaX + deltaDy * deltaY;
        double rest1 = (2 * m2 / (mass + m2)) * (dot / distanceSquared);
        double rest2 = (2 * mass / (m2 + mass)) * (dot / distanceSquared);

        double newDx1 = dx + rest1 * deltaX;
        double newDy1 = dy + rest1 * deltaY;

        double newDx2 = dx2 - rest2 * deltaX;
        double newDy2 = dy2 - rest2 * deltaY;

        this.dx = newDx1;
        this.dy = newDy1;

        other.changeDX(newDx2);
        other.changeDY(newDy2);
    }

    // EFFECTS: calculates distance between this ball and other ball
    public double getDistance(Ball other) {
        return Math.sqrt((x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY()));
    }

    // EFFECTS: calculates next distance between this ball and other ball
    public double getNextDistance(Ball other) {
        return Math.sqrt(((x + dx) - (other.getX() + other.getDX())) * ((x + dx) - (other.getX() + other.getDX())) + 
                ((y + dy) - (other.getY() + other.getDY())) * ((y + dy) - (other.getY() + other.getDY())));
    }

    // MODIFIES: this
    // EFFECTS: checks whether ball is dead (has health < 0)
    public void checkStatus(Pane scene, BallsList balls) {
        if (health <= 0) {
            scene.getChildren().remove(this.getBall());
            balls.removeBall(this);
        }
    }

    // MODIFIES: target, this
    // EFFECTS: inflicts damage onto the target and accounts for target's damage onto this
    public abstract void handleDamage(Ball target);

    // MODOFIES: target, this
    // EFFECTS: scales damage accordingly for both balls
    public abstract void scalePower();

    public StackPane getBall() {
        return ball;
    }

    public double getNextX() {
        return x + dx;
    }

    public double getNextY() {
        return y + dy;
    }

    public Color getColor() {
        return color;
    }

    public void changeDX(double dx) {
        this.dx = dx;
    }

    public void changeDY(double dy) {
        this.dy = dy;
    }

    public void changeX(double x) {
        this.x = x;
    }

    public void changeY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDX() {
        return this.dx;
    }

    public double getDY() {
        return this.dy;
    }

    public double getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

    public void changeDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public abstract String getTag();

}
