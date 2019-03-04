package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class Forward extends TurtleCommand {

    public Forward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) myParams.get(0));
        return (double) myParams.get(0);
    }
}
