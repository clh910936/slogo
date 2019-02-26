package Commands;

public class Tangent extends OneParamCommand {
    public Tangent() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.tan(Math.toRadians(input));
    }
}
