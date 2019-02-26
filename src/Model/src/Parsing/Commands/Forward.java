package Parsing.Commands;

public class Forward extends OneParamCommand {

    public Forward() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.moveForward(input);
        this.turtle.printTurtleStatus();
        return input;
    }
}
