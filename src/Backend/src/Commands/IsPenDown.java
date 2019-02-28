package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (! myTurtle.getIsPenUp().get(myTurtle.getIsPenUp().size() - 1)) ? 1 : 0;
    }
}
