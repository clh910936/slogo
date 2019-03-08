package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPenSize extends OneParamCommand {

    public SetPenSize(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        myTurtle.setPensize(Double.valueOf(String.valueOf(getMyParams().get(0))));
        return Double.valueOf(String.valueOf(getMyParams().get(0)));
    }
}
