package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.List;

public class Ask extends TwoParamCommand {

    public Ask(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

        //TODO: christina please check this

        List<Integer> savedActiveTurtles = new ArrayList<>();
        savedActiveTurtles = this.getMyTurtleModel().getCurrentActiveTurtles();

        String[] activeTurtles = (String[]) getMyParams().get(0);
        List<Integer> activeTurtleIds = new ArrayList<>();
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
