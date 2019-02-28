package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
