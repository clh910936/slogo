package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

public class ListInput extends ZeroParamCommand {

    private String[] myList;

    public ListInput(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager, String[] myList) {
        super(syntaxHandlerFactory, modelManager);
        this.myList = myList;
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return myList;
    }
}
