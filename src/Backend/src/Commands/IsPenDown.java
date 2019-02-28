package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return (! myTurtle.getIsPenUp()) ? 1 : 0;
    }
}
