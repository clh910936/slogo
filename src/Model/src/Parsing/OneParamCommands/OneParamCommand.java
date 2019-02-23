package Parsing.OneParamCommands;

import Parsing.CommandsInfo;

public abstract class OneParamCommand implements CommandsInfo {
    protected double input;
    private boolean isReady;

    public OneParamCommand() {
        isReady = false;
    }

    @Override
    public void addParameterToCommand(double val) {
        this.input = val;
        isReady = true;
    }

    @Override
    public boolean isCommandReadyToRemove() {
        return isReady;
    }

    public abstract double executeCommand();




}
