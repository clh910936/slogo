package Commands;

public class HideTurtle extends ZeroParamCommand {

    public HideTurtle(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setHideTurtle();
        return 0;
    }
}
