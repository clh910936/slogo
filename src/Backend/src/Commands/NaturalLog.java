package Commands;

public class NaturalLog extends OneParamCommand {
    public NaturalLog(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.log((double) input);
    }

}
