package Parsing.Commands;

public class SetTowards extends TwoParamCommand {

    public SetTowards() {
        super();
    }

    @Override
    public double executeCommand() {
        // TODO: talk to front-end and return the right thing
        return input1;
    }
}
