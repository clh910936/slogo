package Commands;

public class Right extends OneParamCommand {

    public Right() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.turnCounterClockwise(-input);
        return input;
    }
}
