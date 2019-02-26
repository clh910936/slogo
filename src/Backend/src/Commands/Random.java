package Commands;

public class Random extends OneParamCommand {
    public Random() {
        super();
    }

    @Override
    public double executeCommand() {
        return (int)(Math.random() * (input));
    }
}
