package Parsing.OneParamCommands;

public class ArcTangent extends OneParamCommand {
    public ArcTangent() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.atan(Math.toRadians(input));
    }
}
