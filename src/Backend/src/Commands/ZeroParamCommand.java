package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
