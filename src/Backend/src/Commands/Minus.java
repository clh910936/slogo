package Commands;

import BackExternal.IllegalParametersException;

public class Minus extends OneParamCommand {
    public Minus(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        return -1 * (double) input;
    }
}
