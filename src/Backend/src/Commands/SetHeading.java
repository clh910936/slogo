package Commands;

import Models.ModelManager;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) input);
        this.myTurtle.setHeadingAngle((double) input);
        return difference;
    }
}
