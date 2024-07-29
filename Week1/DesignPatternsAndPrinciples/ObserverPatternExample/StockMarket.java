package Week1.DesignPatternsAndPrinciples.ObserverPatternExample;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock{
    private List<Observer> observers = new ArrayList<>();

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
