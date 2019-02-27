package Commands;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;
import Models.TurtleModel;

public abstract class CommandsGeneral {
    protected TurtleModel myTurtle;

    public abstract boolean isCommandReadyToExecute();
    public abstract void addParameterToCommand(Object val) throws ParamsExceedLimitException;
    public abstract double executeCommand() throws InsufficientParamsException;
    public abstract String getCommandName();

    public void addParameterToCommand(TurtleModel val) throws ParamsExceedLimitException {
        myTurtle = val;
    }

    public void addParameterToCommand(double val) throws ParamsExceedLimitException {
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();
        if (numOfInputs == 1) {
            this.input2 = val;
            numOfInputs++;
        }
    }

    public void addParameterToCommand(String val) throws ParamsExceedLimitException {
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();
        if (numOfInputs == 0) {
            this.input1 = val;
            numOfInputs++;
        }
    }


}
