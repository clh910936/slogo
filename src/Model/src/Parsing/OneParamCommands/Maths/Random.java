package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class Random extends OneParamCommand {
    public Random() {
        super();
    }

    @Override
    public double executeCommand() {
        return (int)(Math.random() * (input));
    }
}
