package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsGeneral;

public abstract class OneParamCommand implements CommandsGeneral {
    protected double input;
    private boolean isReady;

    public OneParamCommand() {
        isReady = false;
    }

    @Override
    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
        if (isReady) throw new ParamsExceedLimitException();
        this.input = (double) val;
        isReady = true;
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return isReady;
    }

    @Override
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
