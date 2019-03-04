package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class SetPosition extends TwoParamCommand {

    public SetPosition(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        double dist = myTurtle.getDistToPoint((double) myParams.get(0), (double) myParams.get(1));
        myTurtle.updatePoints((double) myParams.get(0), (double) myParams.get(1));

        return dist;
    }
}
