package Commands;

public class Remainder extends TwoParamCommand {
    public Remainder() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 % (double) input2;
    }
}
