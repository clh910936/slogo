package Commands;


import BackExternal.InsufficientParamsException;
import BackExternal.ParamsExceedLimitException;
import Models.TurtleModel;

import java.util.List;

public abstract class CommandsGeneral {
    protected TurtleModel myTurtle;
    protected List<Object> myParams;

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws InsufficientParamsException;

    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
        if( val instanceof TurtleModel) {
            myTurtle = (TurtleModel) val;
            return;
        }
        myParams.add(val);
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
