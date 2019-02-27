package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class ShowTurtle extends ZeroParamCommand {

    public ShowTurtle(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setShowTurtle();
        return 1;
    }
}
