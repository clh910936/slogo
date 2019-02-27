package Commands;

public class ShowTurtle extends ZeroParamCommand {

    public ShowTurtle(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setShowTurtle();
        return 1;
    }
}
