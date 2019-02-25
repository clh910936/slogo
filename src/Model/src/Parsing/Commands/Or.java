package Parsing.Commands;

public class Or extends TwoParamCommand {

    public Or() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 != 0 || input2 != 0)? 1 : 0;
    }
}
