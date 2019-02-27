package Commands;

public class SetHeading extends OneParamCommand {

    public SetHeading() {
        super();
    }

    @Override
    public double executeCommand() {
        double difference = this.turtle.getDegreesDifference(input);
        this.turtle.setHeadingAngle(input);
        return difference;
    }
}
