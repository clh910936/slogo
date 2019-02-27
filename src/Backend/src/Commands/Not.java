package Commands;


public class Not extends OneParamCommand {

    public Not(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException  {
        return ((double) input == 0)? 1 : 0;
    }
}
