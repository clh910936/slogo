package Commands;


import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Not extends OneParamCommand {

    public Not(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return ((double) input == 0)? 1 : 0;
    }
}
