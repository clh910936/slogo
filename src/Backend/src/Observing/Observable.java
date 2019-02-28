package Observing;

import java.util.List;

public abstract class Observable {
    protected List<Observer> myObservers;

    public void add(Observer observer) {
        this.myObservers.add(observer);
    }

    public void remove(Observer observer) {
        this.myObservers.remove(observer);
    }

    public void notifyObservers() {

        for (Observer observer : myObservers) {
            observer.update();
        }
        System.out.println(myObservers.size());
    }
}
