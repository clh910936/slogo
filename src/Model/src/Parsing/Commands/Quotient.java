package Parsing.Commands;

public class Quotient extends TwoParamCommand {

    public Quotient() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 / input2;
    }
}
