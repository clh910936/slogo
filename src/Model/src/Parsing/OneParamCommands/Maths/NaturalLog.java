package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class NaturalLog extends OneParamCommand {
    public NaturalLog() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.log(input);
    }

}
