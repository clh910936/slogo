package Commands;

import BackExternal.ModelManager;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.List;

public class AskWith extends TwoParamCommand {

    public AskWith(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

//        TODO: christina please check this

        List<Integer> savedActiveTurtles = new ArrayList<>();
        savedActiveTurtles = this.myTurtleModel.getCurrentActiveTurtles();

        List<Integer> newActiveTurtles = new ArrayList<>();

        for (Integer currentTurtle : newActiveTurtles) {
            makeTurtleIndexActiveTurtle(currentTurtle);
            String[] condition = (String[]) myParams.get(0);
            String conditionString = String.join(" ", condition);
            CommandParser cp = new CommandParser(myModelManager);
            double out = cp.parseCommand(conditionString, myLanguage);
            if (out != 0) {
                newActiveTurtles.add(currentTurtle);
            }
        }

        this.myTurtleModel.clearCurrentActiveTurtles();
        this.myTurtleModel.setCurrentActiveTurtles(newActiveTurtles);

        CommandParser cp = new CommandParser(myModelManager);
        String[] commands = (String[]) myParams.get(1);
        String commandString = String.join(" ", commands);
        double out = cp.parseCommand(commandString, myLanguage);

        this.myTurtleModel.clearCurrentActiveTurtles();
        this.myTurtleModel.setCurrentActiveTurtles(savedActiveTurtles);

        return out;
    }

    private void makeTurtleIndexActiveTurtle(Integer currentTurtle) {
        this.myTurtleModel.clearCurrentActiveTurtles();
        List<Integer> thisTurtleAsActive = new ArrayList<>();
        thisTurtleAsActive.add(currentTurtle);
        this.myTurtleModel.setCurrentActiveTurtles(thisTurtleAsActive);
    }
}
