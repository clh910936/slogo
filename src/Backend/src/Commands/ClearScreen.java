package Commands;

public class ClearScreen extends ZeroParamCommand {

    public ClearScreen(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint(0,0);

        // TODO: talk to feroze

        return dist;
    }
}
