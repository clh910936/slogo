package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class HideTurtle extends ZeroParamCommand {

    public HideTurtle(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setHideTurtle();
        return 0;
    }
}
