package Commands;

public class ArcTangent extends OneParamCommand {
    public ArcTangent(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.atan(Math.toRadians((double) input));
    }
}
