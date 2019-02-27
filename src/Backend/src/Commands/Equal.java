package Commands;

public class Equal extends TwoParamCommand {

    public Equal() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (input1 == input2)? 1 : 0;
    }
}
