package Commands;

import Models.ModelManager;

public class StringInput extends ZeroParamCommand {

    private String myString;

    public StringInput(String language, ModelManager modelManager, String myString) {
        super(language, modelManager);
        this.myString = myString;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myString;
    }
}
