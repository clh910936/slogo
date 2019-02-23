package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class Minus extends OneParamCommand {
    public Minus() {
        super();
    }

    @Override
    public double executeCommand() {
        return -1 * input;
    }
}
