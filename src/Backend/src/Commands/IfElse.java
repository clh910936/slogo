package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.CommandParser;

public class IfElse extends ThreeParamCommand {

    public IfElse(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    //getMyParams().get(0) : expr
    //getMyParams().get(1) : commands - true
    //input3 : commands - false

    @Override
    public Object executeCommand() throws ClassCastException {
        double out;
        try {
            double expr =  Double.parseDouble(String.valueOf(getMyParams().get(0)));
            String[] commandsTrue = (String[]) getMyParams().get(1);
            String[] commandsFalse = (String[]) getMyParams().get(2);
            String[] commandsToExecute;
            if (expr != 0) {
                commandsToExecute = commandsTrue;
            } else {
                commandsToExecute = commandsFalse;
            }
            String commandString = String.join(" ", commandsToExecute);
            out = getCp().parseCommand(commandString);
        }
        catch (Exception e) {
            return 0;
        }
        return out;
    }
}
