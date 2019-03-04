package Commands;


import Models.ModelManager;

public abstract class ThreeParamCommand extends CommandNode {
    private static final int MAX_PARAMS = 3;

    public ThreeParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return myChildren.size() == MAX_PARAMS;
    }


}
