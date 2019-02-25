package Parsing.Commands;

public class NotEqual extends TwoParamCommand{

    public NotEqual() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 != input2)? 1 : 0;
    }
}
