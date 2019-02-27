package Commands;

import BackExternal.IllegalParametersException;

public class Left extends OneParamCommand {
    public Left() {
        super();
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        this.turtle.turnCounterClockwise((double) input);
        return (double) input;
    }
}
