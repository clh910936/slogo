package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class GetPenColor extends ZeroParamCommand {

    public GetPenColor(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        return myTurtle.getPenColorIndex();
    }
}
