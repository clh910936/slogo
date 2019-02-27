package Commands;


public class Sum extends TwoParamCommand {
    public Sum() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException  {
        return (double) input1 + (double) input2;
    }
}
