package Commands;

public class Home extends ZeroParamCommand {

    public Home(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(0,0);
        myTurtle.updatePoints(0,0);
        myTurtle.setPenDown();
        return dist;
    }
}
