package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.SyntaxHandlerFactory;

public class StringInput extends ZeroParamCommand {

    private String myString;

    public StringInput(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager, String myString) {
        super(syntaxHandlerFactory, modelManager);
        this.myString = myString;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myString;
    }
}
