package Parsing;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;

import java.util.Collection;

public interface CommandsInfo extends CommandsGeneral{

    void addParameterToCommand(double val) throws ParamsExceedLimitException;



    double executeCommand() throws InsufficientParamsException;

    String getCommandName();

}
