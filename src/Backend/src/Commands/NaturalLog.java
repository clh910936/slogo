package Commands;

public class NaturalLog extends OneParamCommand {
    public NaturalLog() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.log((double) input);
    }

}
