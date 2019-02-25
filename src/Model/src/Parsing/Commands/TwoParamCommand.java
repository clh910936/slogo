package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsInfo;

public abstract class TwoParamCommand implements CommandsInfo {
    public static final int MAX_PARAMS = 2;
    protected double input1;
    protected double input2;
    private int numOfInputs;

    public TwoParamCommand() {
        numOfInputs = 0;
    }

    @Override
    public void addParameterToCommand(double val) throws ParamsExceedLimitException {
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();

        if (numOfInputs == 0) {
            this.input1 = val;
        }
        if (numOfInputs == 1) {
            this.input2 = val;
        }
        numOfInputs++;
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
