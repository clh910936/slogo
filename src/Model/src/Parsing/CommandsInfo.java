package Parsing;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;

public interface CommandsInfo {

    void addParameterToCommand(double val) throws ParamsExceedLimitException;

    boolean isCommandReadyToRemove();

    double executeCommand() throws InsufficientParamsException;

    String getCommandName();

}
