package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetTowards extends TwoParamCommand {

    public SetTowards(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        double newAngle = myTurtle.getAngleToPoint(Double.parseDouble(getMyParams().get(0).toString()), Double.parseDouble(getMyParams().get(1).toString()));
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
