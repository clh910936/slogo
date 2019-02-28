package Commands;


import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Sum extends TwoParamCommand {
    public Sum(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return (double) input1 + (double) input2;
    }
}
