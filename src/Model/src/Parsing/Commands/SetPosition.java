package Parsing.Commands;

public class SetPosition extends OneParamCommand {

    public SetPosition() {
        super();
    }

    @Override
    public double executeCommand() {
        // TODO: talk to front-end and return the right thing
        return input;
    }
}
