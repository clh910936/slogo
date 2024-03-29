package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPosition extends TwoParamCommand {

    public SetPosition(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(Double.parseDouble(getMyParams().get(0).toString()), Double.parseDouble(getMyParams().get(1).toString()));
        myTurtle.updatePoints(Double.parseDouble(getMyParams().get(0).toString()) + Turtle.STARTX, Double.parseDouble(getMyParams().get(1).toString()) + Turtle.STARTY);
        myTurtle.setPenDown();
        return dist;
    }
}
