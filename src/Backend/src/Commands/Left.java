package Commands;

public class Left extends OneParamCommand {
    public Left() {
        super();
    }

    @Override
    public double executeCommand() {
        // TODO: talk to front-end and return the right thing
        return input;
    }
}
