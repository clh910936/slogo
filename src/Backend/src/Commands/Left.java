package Commands;

import BackExternal.IllegalParametersException;

public class Left extends OneParamCommand {
    public Left(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.turnCounterClockwise((double) input);
        return (double) input;
    }
}
