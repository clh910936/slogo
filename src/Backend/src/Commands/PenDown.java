package Commands;

public class PenDown extends ZeroParamCommand {

    public PenDown(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenDown();
        return 1;
    }
}
