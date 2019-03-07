package Commands;

import BackExternal.ModelManager;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.List;

public class Ask extends TwoParamCommand {

    public Ask(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

        //TODO: christina please check this

        List<Integer> savedActiveTurtles = new ArrayList<>();
        savedActiveTurtles = this.myTurtleModel.getCurrentActiveTurtles();

        String[] activeTurtles = (String[]) myParams.get(0);
        List<Integer> activeTurtleIds = new ArrayList<>();
        for(String i : activeTurtles) {
            activeTurtleIds.add(Integer.parseInt(i));
        }

        this.myTurtleModel.clearCurrentActiveTurtles();
        this.myTurtleModel.setCurrentActiveTurtles(activeTurtleIds);

        CommandParser cp = new CommandParser(myModelManager);
        String[] commands = (String[]) myParams.get(1);
        String commandString = String.join(" ", commands);
        double out = cp.parseCommand(commandString, myLanguage);

        this.myTurtleModel.clearCurrentActiveTurtles();
        this.myTurtleModel.setCurrentActiveTurtles(savedActiveTurtles);

        return out;

    }
}
