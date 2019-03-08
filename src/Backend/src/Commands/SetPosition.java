package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPosition extends TwoParamCommand {

    public SetPosition(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        double dist = myTurtle.getDistToPoint((double) getMyParams().get(0), (double) getMyParams().get(1));
        myTurtle.updatePoints((double) getMyParams().get(0), (double) getMyParams().get(1));

        return dist;
    }
}
