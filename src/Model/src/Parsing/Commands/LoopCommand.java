package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.LoopCommandsInfo;

public abstract class LoopCommand implements LoopCommandsInfo {
    public static final int MAX_PARAMS = 2;
    private int numOfInputs;
    protected String[] input1;
    protected String[] input2;


    public LoopCommand() {
        numOfInputs = 0;
    }

    @Override
    public void addListParameterToCommand(String[] listOfParams) throws ParamsExceedLimitException{
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();
        if (numOfInputs == 0) {
            this.input1 = listOfParams;
        }
        if (numOfInputs == 1) {
            this.input2 = listOfParams;
        }
        numOfInputs++;
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return (input1 != null && input2 != null);
    }




}
