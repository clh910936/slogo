package Commands;


import Models.ModelManager;

public class Not extends OneParamCommand {

    public Not(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return ((double) myParams.get(0) == 0)? 1 : 0;
    }
}
