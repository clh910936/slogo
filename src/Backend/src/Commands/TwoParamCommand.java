package Commands;

import Parsing.CommandsGeneral;
import Models.TurtleModel;

public abstract class TwoParamCommand implements CommandsGeneral {
    public static final int MAX_PARAMS = 2;
    protected TurtleModel turtle;
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
