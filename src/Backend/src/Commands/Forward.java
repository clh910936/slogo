package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Forward extends OneParamCommand {

    public Forward(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) input);
        return (double) input;
    }
}
