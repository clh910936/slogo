package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public abstract class OneParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 1;
    protected Object input;
    public OneParamCommand(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }


    @Override
    public boolean isCommandReadyToExecute() {
        if(myParams.size() == MAX_PARAMS) {
            input = myParams.get(0);
            return true;
        }
        return false;
    }


}
