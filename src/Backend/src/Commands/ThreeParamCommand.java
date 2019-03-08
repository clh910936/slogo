package Commands;


import BackExternal.ModelManager;

public abstract class ThreeParamCommand extends CommandNode {

    public ThreeParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        setMaxParams(3);
    }




}
