package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;


public abstract class TwoParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 2;
    protected Object input1;
    protected Object input2;

    public TwoParamCommand(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        if(myParams.size() == MAX_PARAMS) {
            input1 = myParams.get(0);
            input2 = myParams.get(1);
            return true;
        }
        return false;
    }


}
