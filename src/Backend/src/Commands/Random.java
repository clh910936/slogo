package Commands;

public class Random extends OneParamCommand {
    public Random() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (int)(Math.random() * (double) (input));
    }
}
