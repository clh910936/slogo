package Parsing;

import Exceptions.InsufficientParamsException;
import Exceptions.ParamsExceedLimitException;
import Turtle.TurtleModel;

public interface CommandsGeneral {
    boolean isCommandReadyToExecute();
    void addParameterToCommand(Object val) throws ParamsExceedLimitException;
    double executeCommand() throws InsufficientParamsException;
    String getCommandName();

}
