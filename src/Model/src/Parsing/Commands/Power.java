package Parsing.Commands;

public class Power extends TwoParamCommand{
    public Power() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.pow(input1, input2);
    }
}
