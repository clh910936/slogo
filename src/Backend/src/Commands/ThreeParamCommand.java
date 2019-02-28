package Commands;


import Models.ModelManager;

public abstract class ThreeParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 3;
    protected Object input1;
    protected Object input2;
    protected Object input3;

    public ThreeParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        if(myParams.size() == MAX_PARAMS) {
            input1 = myParams.get(0);
            input2 = myParams.get(1);
            input3 = myParams.get(2);
            return true;
        }
        return false;
    }


}
