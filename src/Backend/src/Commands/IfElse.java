package Commands;

import Models.ModelManager;
import Parsing.CommandParser;

public class IfElse extends ThreeParamCommand {

    public IfElse(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    //input1 : expr
    //input2 : commands - true
    //input3 : commands - false

    @Override
    public double executeCommand() throws ClassCastException {
        double out;
        try {
            double expr =  Double.parseDouble(String.valueOf(input1));
            String[] commandsTrue = (String[]) input2;
            String[] commandsFalse = (String[]) input3;
            String[] commandsToExecute;
            if (expr != 0) {
                commandsToExecute = commandsTrue;
            } else {
                commandsToExecute = commandsFalse;
            }
            String commandString = String.join(" ", commandsToExecute);
            CommandParser cp = new CommandParser(myModelManager);
            out = cp.parseCommand(commandString, myLanguage);
        }
        catch (Exception e) {
            return 0;
        }
        return out;
    }
}
