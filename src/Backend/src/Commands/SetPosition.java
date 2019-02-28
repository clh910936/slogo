package Commands;

import Models.ModelManager;

public class SetPosition extends TwoParamCommand {

    public SetPosition(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint((double) input1, (double) input2);
        myTurtle.updatePoints((double) input1, (double) input2);
        return dist;
    }
}
