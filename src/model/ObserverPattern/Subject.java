package model.ObserverPattern;

import model.Ball;

public interface Subject extends Observer {
    public void add(Observer observer);

    public void remove(Observer observer);

    public void update(Ball ball1, Ball ball2);

}
