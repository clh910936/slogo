package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class SetTowards extends TwoParamCommand {

    public SetTowards(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        double newAngle = myTurtle.getAngleToPoint((double) input1, (double) input2);
        double diff = myTurtle.getDegreesDifference(newAngle);
        myTurtle.setHeadingAngle(newAngle);
        return diff;
    }
}
