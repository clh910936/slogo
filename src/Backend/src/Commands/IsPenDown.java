package Commands;

public class IsPenDown extends ZeroParamCommand {
    public IsPenDown(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (! myTurtle.getIsPenUp()) ? 1 : 0;
    }
}
