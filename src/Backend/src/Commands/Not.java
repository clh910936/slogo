package Commands;


import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Not extends OneParamCommand {

    public Not(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return ((double) input == 0)? 1 : 0;
    }
}
