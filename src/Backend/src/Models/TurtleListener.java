package Models;

public class TurtleListener extends Observer {

    private TurtleModel myObservable;

    public TurtleListener(TurtleModel tm) {
        this.myObservable = tm;
    }

    @Override
    public void update() {
        System.out.println("Observed: (" + myObservable.getNextPointX() + "," + myObservable.getNextPointY() + ")");
    }
}
