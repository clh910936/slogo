package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        double newAngle = myTurtle.getAngleToPoint((double) input1, (double) input2);
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
