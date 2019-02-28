package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Or extends TwoParamCommand {

    public Or(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return ((double) input1 != 0 || (double) input2 != 0)? 1 : 0;
    }
}
