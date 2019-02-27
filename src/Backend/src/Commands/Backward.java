package Commands;

public class Backward extends OneParamCommand {

    public Backward() {
        super();
    }

    @Override
    public double executeCommand() {
        this.turtle.moveForward(-input);
        return input;
    }
}
