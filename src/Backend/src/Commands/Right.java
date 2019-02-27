package Commands;

public class Right extends OneParamCommand {

    public Right() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        this.turtle.turnCounterClockwise(-1 * (double) input);
        return (double) input;
    }
}
