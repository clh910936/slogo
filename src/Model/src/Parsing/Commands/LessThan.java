package Parsing.Commands;

public class LessThan extends TwoParamCommand{

    public LessThan() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 < input2)? 1 : 0;
    }
}
