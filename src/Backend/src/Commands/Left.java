package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;
import Models.Turtle;

public class Left extends OneParamCommand {
    public Left(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        myTurtle.turnCounterClockwise(Double.valueOf(String.valueOf(myParams.get(0))));
        return (double) myParams.get(0);
    }
}
