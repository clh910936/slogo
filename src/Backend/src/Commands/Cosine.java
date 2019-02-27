package Commands;

public class Cosine extends OneParamCommand {
    public Cosine(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.cos(Math.toRadians((double) input));
    }
}
