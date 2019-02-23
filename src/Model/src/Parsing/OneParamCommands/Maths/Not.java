package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class Not extends OneParamCommand {

    public Not() {
        super();
    }

    @Override
    public double executeCommand()  {
        return (input == 0)? 1 : 0;
    }

}
