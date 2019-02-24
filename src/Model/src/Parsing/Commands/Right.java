package Parsing.Commands;

public class Right extends OneParamCommand {

    public Right() {
        super();
    }

    @Override
    public double executeCommand() {
        // TODO: talk to front-end and return the right thing
        return input;
    }
}
