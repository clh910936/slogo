package Commands;

import BackExternal.IllegalParametersException;

public class GreaterThan extends TwoParamCommand {

    public GreaterThan(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 > (double) input2)? 1 : 0;
    }
}
