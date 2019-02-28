package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Power extends TwoParamCommand {
    public Power(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return Math.pow((double) input1, (double) input2);
    }
}
