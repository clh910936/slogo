package Commands;

import Models.ModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class HideTurtle extends ZeroParamCommand {

    public HideTurtle(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setHideTurtle();
        return 0;
    }
}
