package Commands;

public class Cosine extends OneParamCommand {
    public Cosine() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.cos(Math.toRadians(input));
    }
}
