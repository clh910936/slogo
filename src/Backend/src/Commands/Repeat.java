package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

import java.util.Arrays;


public class Repeat extends TwoParamCommand{
    public static final String REPCOUNT = "repcount";

    public Repeat(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        if (! isCommandReadyToExecute()) return 0;
        try {
            double numOfTimes = Double.valueOf(String.valueOf(getMyParams().get(0)));
            String[] commands = (String[]) getMyParams().get(1);
            System.out.println("COMMANDS*&*&*&*&&" + Arrays.toString(commands));

            double lastValue = 0;
            for (int i = 1; i <= numOfTimes; i++) {
                String commandString = String.join(" ", commands);
                getMyVariablesModel().addVariable(REPCOUNT, Double.toString(i));
                lastValue = getCp().parseCommand(commandString);
                System.out.println("LAST VALUE" + lastValue);
            }
            return lastValue;
        }
        catch (Exception e) {
            return 0;
        }

    }
}
