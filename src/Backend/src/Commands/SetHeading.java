package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) input);
        this.myTurtle.setHeadingAngle((double) input);
        return difference;
    }
}
