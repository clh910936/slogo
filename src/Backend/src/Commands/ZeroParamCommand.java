package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
