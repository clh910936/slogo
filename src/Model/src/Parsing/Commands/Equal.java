package Parsing.Commands;

public class Equal extends TwoParamCommand {

    public Equal() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 == input2)? 1 : 0;
    }
}
