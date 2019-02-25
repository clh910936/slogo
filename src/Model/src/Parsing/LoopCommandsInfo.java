package Parsing;

import Exceptions.IllegalLoopParamsException;

public interface LoopCommandsInfo extends CommandsGeneral{


    void addListParameterToCommand(String[] listOfParams);


    double executeLoop() throws IllegalLoopParamsException;




}
