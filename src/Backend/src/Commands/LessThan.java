package Commands;

import BackExternal.IllegalParametersException;

public class LessThan extends TwoParamCommand {

    public LessThan(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws IllegalParametersException {
        return ((double) input1 < (double) input2)? 1 : 0;
    }
}
