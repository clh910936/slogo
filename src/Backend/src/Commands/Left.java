package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class Left extends OneParamCommand {
    public Left(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.turnCounterClockwise((double) input);
        return (double) input;
    }
}
