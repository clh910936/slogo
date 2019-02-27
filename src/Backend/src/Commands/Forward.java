package Commands;

public class Forward extends OneParamCommand {

    public Forward() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.moveForward(input);
        return input;
    }
}
