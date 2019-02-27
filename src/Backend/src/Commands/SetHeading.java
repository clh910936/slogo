package Commands;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) input);
        this.myTurtle.setHeadingAngle((double) input);
        return difference;
    }
}
