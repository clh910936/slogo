package Parsing.Commands;

public class Forward extends OneParamCommand {

    public Forward() {
        super();
    }

    @Override
    public double executeCommand() {
        //TODO: talk to front-end and return the right thing
        return input;
    }
}
