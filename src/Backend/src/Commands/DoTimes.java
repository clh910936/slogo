package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;
import Parsing.SyntaxHandlerFactory;

public class DoTimes extends TwoParamCommand{
    public static final int VAR_LOC = 0;
    public static final int LIMIT_LOC = 1;

    public DoTimes(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        if (! isCommandReadyToExecute()) return 0;
        try {
            String[] varAndLimit = (String[]) getMyParams().get(0);
            String[] commands = (String[]) getMyParams().get(1);
            String variable = varAndLimit[VAR_LOC];
            double limit = Double.parseDouble(varAndLimit[LIMIT_LOC]);

            double lastValue = 0;
            for (int i = 1; i <= limit; i++) {
                String commandString = String.join(" ", commands);
                String param = String.valueOf(i);
                commandString = commandString.replaceAll(variable, param);
                getMyVariablesModel().addVariable(variable, param);
                lastValue = getCp().parseCommand(commandString);
            }
            return lastValue;
        }
        catch (Exception e) {
            return 0;
        }

    }

}
