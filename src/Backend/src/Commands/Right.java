package Commands;

public class Right extends OneParamCommand {

    public Right(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        this.myTurtle.turnCounterClockwise(-1 * (double) input);
        return (double) input;
    }
}
