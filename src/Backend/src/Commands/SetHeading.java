package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) input);
        this.myTurtle.setHeadingAngle((double) input);
        return difference;
    }
}
