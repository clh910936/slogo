package Commands;

public class And extends TwoParamCommand{

    public And() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return ((double) input1 != 0 && (double) input2 != 0)? 1 : 0;
    }

}
