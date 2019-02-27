package Commands;

import BackExternal.InsufficientParamsException;

public class Sum extends TwoParamCommandDoubles {
    public Sum() {
        super();
    }

    @Override
    public double executeCommand() throws InsufficientParamsException {
        if (! isCommandReadyToExecute()) throw new InsufficientParamsException();
        return input1 + input2;
    }
}
