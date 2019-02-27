package Commands;

public class SetPosition extends TwoParamCommandDoubles {

    public SetPosition() {
        super();
    }

    @Override
    public double executeCommand() {
        double dist = turtle.getDistToPoint(input1, input2);
        turtle.updatePoints(input1, input2);
        return dist;
    }
}
