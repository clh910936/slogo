package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalLoopParamsException;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class For extends TwoParamCommand {

    public static final int NUM_PARAMS = 4;
    public static final int VAR_LOC = 0;
    public static final int START_LOC = 1;
    public static final int END_LOC = 2;
    public static final int INCR_LOC = 3;

    public For(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        double out = 0.0;
        try {
            String[] variablesInfo = (String[]) getMyParams().get(0);
            List<Double> variableValues = getVariableValues(variablesInfo);
            String varName = variablesInfo[0];
            String[] commands = (String[]) getMyParams().get(1);
//            if (variablesInfo.length != NUM_PARAMS) {
//                throw new IllegalLoopParamsException();
//            }
            if (! isCommandReadyToExecute()) {
                return 0;
            }

            if (commands.length == 0) {
                return 0;
            }
            for (int i = 0; i < variableValues.size(); i++) {
                String commandString = String.join(" ", commands);
                String param = String.valueOf(variableValues.get(i));
                commandString = commandString.replaceAll(varName, param);
                out = getCp().parseCommand(commandString);
            }
        }
        catch(IllegalCommandException e) {
            throw new IllegalCommandException("For loop broke");
        }
        return out;
    }




}

