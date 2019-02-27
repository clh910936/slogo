package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;
import Parsing.CommandParser;

public class Repeat extends TwoParamCommand{
    public static final String REPCOUNT = ":repcount";
    private CommandParser cp;

    public Repeat(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
        cp = new CommandParser(new VariablesModel(), myTurtle, myUserCreatedCommandsModel);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        if (! isCommandReadyToExecute()) return 0;
        try {
            double numOfTimes = (double) input1;
            String[] commands = (String[]) input2;

            double lastValue = 0;
            for (int i = 1; i <= numOfTimes; i++) {
                String commandString = String.join(" ", commands);
                lastValue = cp.parseCommand(commandString, myLanguage);
                myVariablesModel.addVariable(REPCOUNT, Double.toString(lastValue));
            }
            return lastValue;
        }
        catch (Exception e) {
            return 0;
        }

    }
}
