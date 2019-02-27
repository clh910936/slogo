package Commands;

import BackExternal.IllegalParametersException;

public class Forward extends OneParamCommand {

    public Forward(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        this.myTurtle.moveForward((double) input);
        return (double) input;
    }
}
