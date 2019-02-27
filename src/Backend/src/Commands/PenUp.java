package Commands;

public class PenUp extends ZeroParamCommand {

    public PenUp(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        return 0;
    }
}
