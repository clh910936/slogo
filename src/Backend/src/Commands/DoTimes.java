package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;
import Parsing.SyntaxHandlerFactory;

import java.util.ArrayList;
import java.util.List;

public class DoTimes extends TwoParamCommand{
    public static final int VAR_LOC = 0;
    public static final int LIMIT_LOC = 1;

    public DoTimes(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        try {
            String[] varAndLimit = (String[]) getMyParams().get(0);
            String varName = varAndLimit[0];
            List<Double> variableValues = new ArrayList<>(getVariableValues(varAndLimit));
            String[] commands = (String[]) getMyParams().get(1);
            String variable = varAndLimit[VAR_LOC];
            double limit = variableValues.get(0);
            if (! isCommandReadyToExecute()) {
                return 0;
            }
            if (commands.length == 0) {
                return 0;
            }
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
