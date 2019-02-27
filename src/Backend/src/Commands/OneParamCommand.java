package Commands;

import Exceptions.ParamsExceedLimitException;
import Models.TurtleModel;

public abstract class OneParamCommand implements CommandsGeneral {
    protected double input;
    private boolean isReady;
    protected TurtleModel turtle;
    public OneParamCommand() {
        isReady = false;
    }

    @Override
    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
        if (val instanceof TurtleModel) {
            turtle = (TurtleModel) val;
            return;
        }
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
