package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class SetPosition extends TwoParamCommand {

    public SetPosition(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint((double) input1, (double) input2);
        myTurtle.updatePoints((double) input1, (double) input2);
        return dist;
    }
}
