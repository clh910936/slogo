package Commands;

public class Difference extends TwoParamCommand {

    public Difference(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 - (double) input2;
    }
}
