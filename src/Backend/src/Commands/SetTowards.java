package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        double newAngle = myTurtle.getAngleToPoint((double) getMyParams().get(0), (double) getMyParams().get(1));
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
