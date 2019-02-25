package Parsing.Commands;

public class And extends TwoParamCommand{

    public And() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 != 0 && input2 != 0)? 1 : 0;
    }
}
