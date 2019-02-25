package Parsing;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;

public interface CommandsInfo extends CommandsGeneral{


    double executeCommand() throws InsufficientParamsException;

    String getCommandName();

}
