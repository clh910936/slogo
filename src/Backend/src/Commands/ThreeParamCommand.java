package Commands;


import Models.ModelManager;

public abstract class ThreeParamCommand extends CommandNode {

    public ThreeParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        MAX_PARAMS = 3;
    }




}
