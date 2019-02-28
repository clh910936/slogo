package Commands;

import Models.ModelManager;
import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class YCoordinate extends ZeroParamCommand {
    public YCoordinate (String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getNextPointY();
    }
}
