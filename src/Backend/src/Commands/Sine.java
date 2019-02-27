package Commands;

public class Sine extends OneParamCommand {

    public Sine() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.sin(Math.toRadians((double) input));
    }
}
