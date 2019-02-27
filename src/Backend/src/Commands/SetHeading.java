package Commands;

public class SetHeading extends OneParamCommand {

    public SetHeading() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.turtle.getDegreesDifference((double) input);
        this.turtle.setHeadingAngle((double) input);
        return difference;
    }
}
