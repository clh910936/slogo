package Parsing;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;

import java.util.Collection;

public interface CommandsInfo {

    void addParameterToCommand(double val) throws ParamsExceedLimitException;

    boolean isCommandReadyToRemove();

    double executeCommand() throws InsufficientParamsException;

    String getCommandName();

}
