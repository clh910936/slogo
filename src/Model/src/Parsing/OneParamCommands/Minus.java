package Parsing.OneParamCommands;

public class Minus extends OneParamCommand {
    public Minus() {
        super();
    }

    @Override
    public double executeCommand() {
        return -1 * input;
    }
}
