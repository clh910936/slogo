package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class Cosine extends OneParamCommand {
    public Cosine() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.cos(Math.toRadians(input));
    }
}
