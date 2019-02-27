package Commands;

public class Backward extends OneParamCommand {

    public Backward(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        this.turtle.moveForward(-(double) input);
        return (double) input;
    }
}
