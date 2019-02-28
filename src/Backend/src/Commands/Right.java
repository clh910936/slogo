package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Right extends OneParamCommand {

    public Right(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        this.myTurtle.turnCounterClockwise(-1 * (double) input);
        return (double) input;
    }
}
