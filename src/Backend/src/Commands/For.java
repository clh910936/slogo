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
            String[] commands = (String[]) getMyParams().get(1);
//            if (variablesInfo.length != NUM_PARAMS) {
//                throw new IllegalLoopParamsException();
//            }
            if (! isCommandReadyToExecute()) {
                return 0;
            }
            List<Double> variableValues = getListOfVariables(variablesInfo);

            if (commands.length == 0) {
                return 0;
            }
            for (int i = 0; i < variableValues.size(); i++) {
                String commandString = String.join(" ", commands);
                String param = String.valueOf(variableValues.get(i));
                commandString = commandString.replaceAll(variablesInfo[0], param);
                out = getCp().parseCommand(commandString);
            }

        }
        catch(IllegalCommandException e) {
            throw new IllegalCommandException("For loop broke");
        }
        return out;
    }
    private List<Double> getListOfVariables(String[] variablesFor) {
        List<Double> variableValues = new ArrayList<>();
        double start = Double.parseDouble((variablesFor)[START_LOC]);
        double end = Double.parseDouble(((variablesFor)[END_LOC]));
        double incr = Double.parseDouble((variablesFor)[INCR_LOC]);
        while (start <= end) {
            variableValues.add(start);
            start += incr;
        }
        return variableValues;
    }





}

