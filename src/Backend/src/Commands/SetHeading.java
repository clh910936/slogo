package Commands;

import Models.ModelManager;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) myParams.get(0));
        this.myTurtle.setHeadingAngle((double) myParams.get(0));
        return difference;
    }
}
