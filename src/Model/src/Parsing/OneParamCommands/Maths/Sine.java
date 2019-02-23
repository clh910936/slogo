package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class Sine extends OneParamCommand {

    public Sine() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.sin(Math.toRadians(input));
    }
}
