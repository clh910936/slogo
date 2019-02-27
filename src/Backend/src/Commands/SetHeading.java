package Commands;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.turtle.getDegreesDifference((double) input);
        this.turtle.setHeadingAngle((double) input);
        return difference;
    }
}
