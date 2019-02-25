package Parsing.Commands;

public class Remainder extends TwoParamCommand{
    public Remainder() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 % input2;
    }
}
