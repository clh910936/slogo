package Commands;

public class Quotient extends TwoParamCommand {

    public Quotient() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 / (double) input2;
    }
}
