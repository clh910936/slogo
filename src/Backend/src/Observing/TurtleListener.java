package Observing;

import Models.Turtle;

public class TurtleListener extends Observer {

    private Turtle myObservable;

    public TurtleListener(Turtle tm) {
        this.myObservable = tm;
    }

    @Override
    public void update() {
        System.out.println("Observed: (" + myObservable.getUpdatedX() + "," + myObservable.getUpdatedY() + ")");
    }
}
