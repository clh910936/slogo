package Parsing.Commands;

import Exceptions.IllegalLoopParamsException;
import Exceptions.InsufficientParamsException;
import Parsing.CommandParser;
import Turtle.TurtleModel;
import Variables.VariablesModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class For extends LoopCommand {

    public static final int NUM_PARAMS = 4;
    public static final int VAR_LOC = 0;
    public static final int START_LOC = 1;
    public static final int END_LOC = 2;
    public static final int INCR_LOC = 3;


    public For(TurtleModel turtleModel) {
        super();
        this.turtle = turtleModel;
    }

    @Override
    public double executeCommand() {
        if (input1.length != NUM_PARAMS) throw new IllegalLoopParamsException();
        if (! isCommandReadyToExecute()) return 0;

        List<Double> variableValues = new ArrayList<>();
        String tmpVar = input1[VAR_LOC];
        double start = Double.parseDouble(input1[START_LOC]);
        double end = Double.parseDouble(input1[END_LOC]);
        double incr = Double.parseDouble(input1[INCR_LOC]);

        while (start <= end) {
            variableValues.add(start);
            start += incr;
        }

        double out = 0.0;
        for (int i = 0; i < variableValues.size(); i++) {
            String[] newCommandArray = Arrays.copyOf(input2, input2.length);
            for (int j = 0; j < input2.length; j++) {
                if (input2[j].equals(tmpVar)) {
                    newCommandArray[j] = variableValues.get(i).toString();
                }
            }
            if(newCommandArray.length==0) return 0;
            // TODO: made just to test || prob be moved
            String newCommand = String.join(" ", newCommandArray);
            CommandParser cp = new CommandParser(new VariablesModel(), turtle);
            //TODO: can't hard code in English
            out = cp.parseCommand(newCommand, "English");
        }
        return out;
    }


}

