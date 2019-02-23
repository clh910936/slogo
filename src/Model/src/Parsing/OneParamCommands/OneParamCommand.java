package Parsing.OneParamCommands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsInfo;

public abstract class OneParamCommand implements CommandsInfo {
    protected double input;
    private boolean isReady;

    public OneParamCommand() {
        isReady = false;
    }

    @Override
    public void addParameterToCommand(double val) throws ParamsExceedLimitException {
        if (isReady) throw new ParamsExceedLimitException();
        this.input = val;
        isReady = true;
    }

    @Override
    public boolean isCommandReadyToRemove() {
        return isReady;
    }

    @Override
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
