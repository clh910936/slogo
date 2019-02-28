package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class Forward extends OneParamCommand {

    public Forward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) input);
        return (double) input;
    }
}
