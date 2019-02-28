package Commands;

import BackExternal.IModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public abstract class OneParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 1;
    protected Object input;
    public OneParamCommand(String language, IModelManager iModelManager) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
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
