package Parsing.Commands;

public class Left extends OneParamCommand {
    public Left() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.turnCounterClockwise(input);
        return input;
    }
}
