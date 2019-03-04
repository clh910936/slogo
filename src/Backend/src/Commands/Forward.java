package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;
import Models.Turtle;

public class Forward extends OneParamCommand {

    public Forward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();
        myTurtle.moveForward((double) myParams.get(0));
        return (double) myParams.get(0);
    }
}
