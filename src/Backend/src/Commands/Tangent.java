package Commands;

public class Tangent extends OneParamCommand {
    public Tangent() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.tan(Math.toRadians((double) input));
    }
}
