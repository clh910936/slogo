package Commands;

public class IsShowing extends ZeroParamCommand {
    public IsShowing(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (myTurtle.getIsDisplayed()) ? 1 : 0;
    }
}
