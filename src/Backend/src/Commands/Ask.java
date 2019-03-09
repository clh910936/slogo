package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.List;

public class Ask extends TwoParamCommand {

    public Ask(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

        List<Integer> savedActiveTurtles = new ArrayList<>(this.getMyTurtleModel().getCurrentActiveTurtles());

        List<Integer> activeTurtleIds = new ArrayList<>();
        String[] activeTurtles = (String[]) getMyParams().get(0);
        for(String i : activeTurtles) {
            activeTurtleIds.add(Integer.parseInt(i));
        }

        this.getMyTurtleModel().clearCurrentActiveTurtles();
        this.getMyTurtleModel().setCurrentActiveTurtles(activeTurtleIds);

        String[] commands = (String[]) getMyParams().get(1);
        String commandString = String.join(" ", commands);
        double out = getCp().parseCommand(commandString);
        this.getMyTurtleModel().clearCurrentActiveTurtles();
        this.getMyTurtleModel().setCurrentActiveTurtles(savedActiveTurtles);

        return out;

    }
}
