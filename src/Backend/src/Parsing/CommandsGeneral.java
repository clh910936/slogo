package Parsing;

import BackExternal.InsufficientParamsException;
import BackExternal.ParamsExceedLimitException;

public interface CommandsGeneral {
    boolean isCommandReadyToExecute();
    void addParameterToCommand(Object val) throws ParamsExceedLimitException;
    double executeCommand() throws InsufficientParamsException;
    String getCommandName();

}
