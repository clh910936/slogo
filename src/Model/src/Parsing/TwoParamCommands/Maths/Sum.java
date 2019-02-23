package Parsing.TwoParamCommands.Maths;

import Exceptions.InsufficientParamsException;
import Parsing.TwoParamCommands.TwoParamCommand;

public class Sum extends TwoParamCommand {
    public Sum() {
        super();
    }

    @Override
    public double executeCommand() throws InsufficientParamsException {
        if (! isCommandReadyToRemove()) throw new InsufficientParamsException();
        return input1 + input2;
    }
}
