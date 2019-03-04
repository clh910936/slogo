package Commands;

import BackExternal.ModelManager;

public class ListInput extends ZeroParamCommand {

    private String[] myList;

    public ListInput(String language, ModelManager modelManager, String[] myList) {
        super(language, modelManager);
        this.myList = myList;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myList;
    }
}
