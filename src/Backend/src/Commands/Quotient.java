package Commands;

public class Quotient extends TwoParamCommand {

    public Quotient(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 / (double) input2;
    }
}
