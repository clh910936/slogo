package Commands;

import BackExternal.IllegalCommandException;
import BackExternal.IllegalLoopParamsException;
import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;
import java.util.ArrayList;
import java.util.List;

public class For extends TwoParamCommand {

    public static final int NUM_PARAMS = 4;
    public static final int VAR_LOC = 0;
    public static final int START_LOC = 1;
    public static final int END_LOC = 2;
    public static final int INCR_LOC = 3;

    public For(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        double out = 0.0;
        try {
            String[] variablesInfo = (String[]) myParams.get(0);
            String[] commands = (String[]) myParams.get(1);

            if (variablesInfo.length != NUM_PARAMS) {
                throw new IllegalLoopParamsException();
            }

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
                CommandParser cp = new CommandParser(myModelManager);
                out = cp.parseCommand(commandString, myLanguage);
            }
        }
        catch(IllegalCommandException e) {
            throw new IllegalCommandException("For loop broke");
        }
        return out;
    }


//    double out = 0.0;
//        for (int i = 0; i < variableValues.size(); i++) {
//        String[] newCommandArray = Arrays.copyOf(((String[])myParams.get(1)), ((String[])myParams.get(1)).length);
//        for (int j = 0; j < ((String[])myParams.get(1)).length; j++) {
//            if (((String[])myParams.get(1))[j].equals(tmpVar)) {
//                newCommandArray[j] = variableValues.get(i).toString();
//            }
//        }
//        if(newCommandArray.length == 0) {
//            return 0;
//        }
//        String newCommand = String.join(" ", newCommandArray);
//        CommandParser cp = new CommandParser(myModelManager);
//        out = cp.parseCommand(newCommand, myLanguage);
//    }

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

