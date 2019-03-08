package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;

public class If extends TwoParamCommand {

    public If(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        double out = 0.0;

        try {
            double expr =  Double.parseDouble(String.valueOf(getMyParams().get(0)));
            String[] commands = (String[]) getMyParams().get(1);
            if (expr != 0) {
                String commandString = String.join(" ", commands);
                CommandParser cp = new CommandParser(myModelManager);
                out = cp.parseCommand(commandString,myLanguage);
            }
        }
        catch (Exception e) {
            return 0;
        }
        return out;




    }
}
