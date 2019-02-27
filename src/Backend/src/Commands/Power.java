package Commands;

public class Power extends TwoParamCommand {
    public Power() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return Math.pow((double) input1, (double) input2);
    }
}
