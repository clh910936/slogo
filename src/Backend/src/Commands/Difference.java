package Commands;

public class Difference extends TwoParamCommand {

    public Difference() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 - (double) input2;
    }
}
