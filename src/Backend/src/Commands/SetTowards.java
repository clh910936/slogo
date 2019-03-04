package Commands;

import Models.ModelManager;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        double newAngle = myTurtle.getAngleToPoint((double) input1, (double) input2);
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
