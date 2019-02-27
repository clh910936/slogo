package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double difference = this.myTurtle.getDegreesDifference((double) input);
        this.myTurtle.setHeadingAngle((double) input);
        return difference;
    }
}
