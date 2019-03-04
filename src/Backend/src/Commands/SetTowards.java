package Commands;

import Models.ModelManager;
import Models.Turtle;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        double newAngle = myTurtle.getAngleToPoint((double) myParams.get(0), (double) myParams.get(1));
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
