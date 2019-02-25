package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsGeneral;

public abstract class TwoParamCommand implements CommandsGeneral {
    public static final int MAX_PARAMS = 2;
    protected int numOfInputs;
    public TwoParamCommand() {
        numOfInputs = 0;
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return (numOfInputs == MAX_PARAMS);
    }

    @Override
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
