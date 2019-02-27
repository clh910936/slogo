package Commands;

import Exceptions.ParamsExceedLimitException;
import Models.TurtleModel;

public abstract class TwoParamCommand implements CommandsGeneral {
    private static final int MAX_PARAMS = 2;
    protected TurtleModel turtle;
    private int numOfInputs;
    protected Object input1;
    protected double input2;

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
