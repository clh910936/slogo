package Commands;

public class Tangent extends OneParamCommand {
    public Tangent(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
