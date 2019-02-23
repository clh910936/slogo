package Parsing;

import Exceptions.ParamsExceedLimitException;

public interface CommandsInfo {

    void addParameterToCommand(double val) throws ParamsExceedLimitException;

    boolean isCommandReadyToRemove();

    double executeCommand();

}
