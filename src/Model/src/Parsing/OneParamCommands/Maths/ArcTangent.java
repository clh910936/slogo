package Parsing.OneParamCommands.Maths;

import Parsing.OneParamCommands.OneParamCommand;

public class ArcTangent extends OneParamCommand {
    public ArcTangent() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.atan(Math.toRadians(input));
    }
}
