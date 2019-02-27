package Commands;

public class NotEqual extends TwoParamCommand {

    public NotEqual() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return ((double) input1 != (double) input2)? 1 : 0;
    }
}
