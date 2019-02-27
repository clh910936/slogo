package Commands;

public class SetTowards extends TwoParamCommand {

    public SetTowards() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double newAngle = turtle.getAngleToPoint((double) input1, (double) input2);
        double diff = turtle.getDegreesDifference(newAngle);
        turtle.setHeadingAngle(newAngle);
        return diff;
    }
}