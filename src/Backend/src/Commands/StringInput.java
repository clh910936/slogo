package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;

public class StringInput extends ZeroParamCommand {

    private String myString;

    public StringInput(ModelManager modelManager
, String myString) {
        super(modelManager
);
        this.myString = myString;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myString;
    }
}
