package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;

public class Repeat extends TwoParamCommand{
    public static final String REPCOUNT = ":repcount";
    private CommandParser cp;

    public Repeat(String language, ModelManager modelManager) {
        super(language, modelManager);
        cp = new CommandParser(modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        if (! isCommandReadyToExecute()) return 0;
        try {
            double numOfTimes = Double.valueOf(String.valueOf(getMyParams().get(0)));
            String[] commands = (String[]) getMyParams().get(1);

            double lastValue = 0;
            for (int i = 1; i <= numOfTimes; i++) {
                String commandString = String.join(" ", commands);
                getMyVariablesModel().addVariable(REPCOUNT, Double.toString(i));
                lastValue = cp.parseCommand(commandString,getMyLanguage());
            }
            return lastValue;
        }
        catch (Exception e) {
            return 0;
        }

    }
}
