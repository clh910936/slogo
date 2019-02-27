package Commands;

public class SetPosition extends TwoParamCommand {

    public SetPosition(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint((double) input1, (double) input2);
        myTurtle.updatePoints((double) input1, (double) input2);
        return dist;
    }
}
