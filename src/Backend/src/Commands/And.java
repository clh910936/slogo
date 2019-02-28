package Commands;

import BackExternal.IModelManager;
import Models.ModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class And extends TwoParamCommand{

    public And(String language, IModelManager modelManager) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return ((double) input1 != 0 && (double) input2 != 0)? 1 : 0;
    }

}
