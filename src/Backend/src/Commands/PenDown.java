package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class PenDown extends ZeroParamCommand {

    public PenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        myTurtle.setPenDown();
        return 1;
    }
}
