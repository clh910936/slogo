package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;
import Parsing.CommandParser;

import java.util.Arrays;

public class DoTimes extends TwoParamCommand{
    private CommandParser cp;
    public static final int VAR_LOC = 0;
    public static final int LIMIT_LOC = 1;

    public DoTimes(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
        cp = new CommandParser(new VariablesModel(), myTurtle, myUserCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        if (! isCommandReadyToExecute()) return 0;
        try {
            String[] varAndLimit = (String[]) input1;

            String[] commands = (String[]) input2;
            String variable = varAndLimit[VAR_LOC];
            double limit = Double.parseDouble(varAndLimit[LIMIT_LOC]);

            double lastValue = 0;
            for (int i = 1; i <= limit; i++) {
                String commandString = String.join(" ", commands);
                String param = String.valueOf(i);
                commandString = commandString.replaceAll(variable, param);
                myVariablesModel.addVariable(variable, param);
                lastValue = cp.parseCommand(commandString, myLanguage);
            }
            return lastValue;
        }
        catch (Exception e) {
            return 0;
        }

    }

}
