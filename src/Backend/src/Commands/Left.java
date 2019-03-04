package Commands;

import BackExternal.IllegalParametersException;
import Models.ModelManager;
import Models.ModelManager;

public class Left extends OneParamCommand {
    public Left(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws IllegalParametersException {
        this.myTurtle.turnCounterClockwise((double) myParams.get(0));
        return (double) myParams.get(0);
    }
}
