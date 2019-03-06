package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class Right extends OneParamCommand {

    public Right(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        myTurtle.turnCounterClockwise(-1 * Double.valueOf(String.valueOf(myParams.get(0))));
        return myParams.get(0);
    }
}
