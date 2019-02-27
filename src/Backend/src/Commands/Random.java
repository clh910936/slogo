package Commands;

public class Random extends OneParamCommand {
    public Random(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (int)(Math.random() * (double) (input));
    }
}
