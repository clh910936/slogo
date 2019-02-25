package Parsing.Commands;

public class Difference extends TwoParamCommand{

    public Difference() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 - input2;
    }
}
