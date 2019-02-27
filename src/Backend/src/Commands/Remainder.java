package Commands;

public class Remainder extends TwoParamCommand {
    public Remainder(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 % (double) input2;
    }
}
