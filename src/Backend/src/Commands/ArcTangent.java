package Commands;

public class ArcTangent extends OneParamCommand {
    public ArcTangent() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.atan(Math.toRadians((double) input));
    }
}
