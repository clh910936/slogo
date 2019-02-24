package Parsing.Commands;

import Exceptions.InsufficientParamsException;

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
