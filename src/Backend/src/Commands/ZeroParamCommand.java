package Commands;

import Models.ModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
