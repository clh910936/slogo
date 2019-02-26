package Parsing.Commands;

public class SetTowards extends TwoParamCommandDoubles {

    public SetTowards() {
        super();
    }

    @Override
    public double executeCommand() {
        double newAngle = turtle.getAngleToPoint(input1, input2);
        double diff = turtle.getDegreesDifference(newAngle);
        turtle.setHeadingAngle(newAngle);
        return diff;
    }
}
