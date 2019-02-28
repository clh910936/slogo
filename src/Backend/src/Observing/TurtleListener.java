package Observing;

import Models.Turtle;
import Observing.Observer;

public class TurtleListener extends Observer {

    private Turtle myObservable;

    public TurtleListener(Turtle tm) {
        this.myObservable = tm;
    }

    @Override
    public void update() {
        System.out.println("Observed: (" + myObservable.getNextPointX() + "," + myObservable.getNextPointY() + ")");
    }
}
