package Commands;

import BackExternal.IllegalLoopParamsException;
import BackExternal.IllegalParametersException;
import Parsing.CommandParser;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class For extends TwoParamCommand {

    public static final int NUM_PARAMS = 4;
    public static final int VAR_LOC = 0;
    public static final int START_LOC = 1;
    public static final int END_LOC = 2;
    public static final int INCR_LOC = 3;


    public For(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        if (((String[])input1).length != NUM_PARAMS) throw new IllegalLoopParamsException();
        if (! isCommandReadyToExecute()) return 0;

        List<Double> variableValues = new ArrayList<>();
        String tmpVar = ((String[])input1)[VAR_LOC];
        double start = Double.parseDouble(((String[])input1)[START_LOC]);
        double end = Double.parseDouble(((String[])input1)[END_LOC]);
        double incr = Double.parseDouble(((String[])input1)[INCR_LOC]);

        while (start <= end) {
            variableValues.add(start);
            start += incr;
        }

        double out = 0.0;
        for (int i = 0; i < variableValues.size(); i++) {
            String[] newCommandArray = Arrays.copyOf(((String[])input2), ((String[])input2).length);
            for (int j = 0; j < ((String[])input2).length; j++) {
                if (((String[])input2)[j].equals(tmpVar)) {
                    newCommandArray[j] = variableValues.get(i).toString();
                }
            }
            if(newCommandArray.length==0) return 0;
            String newCommand = String.join(" ", newCommandArray);
            CommandParser cp = new CommandParser(new VariablesModel(), myTurtle);
            out = cp.parseCommand(newCommand, myLanguage);
        }
        return out;
    }


}

