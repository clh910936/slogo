package Commands;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double newAngle = myTurtle.getAngleToPoint((double) input1, (double) input2);
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
