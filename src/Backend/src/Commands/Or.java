package Commands;

public class Or extends TwoParamCommand {

    public Or(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException  {
        return ((double) input1 != 0 || (double) input2 != 0)? 1 : 0;
    }
}
