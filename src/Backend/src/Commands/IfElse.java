package Commands;

import BackExternal.ModelManager;
import Parsing.CommandParser;

public class IfElse extends ThreeParamCommand {

    public IfElse(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    //myParams.get(0) : expr
    //myParams.get(1) : commands - true
    //input3 : commands - false

    @Override
    public Object executeCommand() throws ClassCastException {
        double out;
        try {
            double expr =  Double.parseDouble(String.valueOf(myParams.get(0)));
            String[] commandsTrue = (String[]) myParams.get(1);
            String[] commandsFalse = (String[]) myParams.get(2);
            String[] commandsToExecute;
            if (expr != 0) {
                commandsToExecute = commandsTrue;
            } else {
                commandsToExecute = commandsFalse;
            }
            String commandString = String.join(" ", commandsToExecute);
            CommandParser cp = new CommandParser(myModelManager,myLanguage);
            out = cp.parseCommand(commandString);
        }
        catch (Exception e) {
            return 0;
        }
        return out;
    }
}
