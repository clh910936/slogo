package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

import java.util.ArrayList;
import java.util.List;

public class AskWith extends TwoParamCommand {
    public AskWith(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

        List<Integer> savedActiveTurtles = this.getMyTurtleModel().getCurrentActiveTurtles();
        List<Integer> newActiveTurtles = new ArrayList<>();
        getTurtlesOfCondition(newActiveTurtles);
        setActiveTurtles(newActiveTurtles);
        double returnValue = parseCommand();
        setActiveTurtles(savedActiveTurtles);
        return returnValue;
    }

    private double parseCommand() {
        String[] commands = (String[]) getMyParams().get(1);
        String commandString = String.join(" ", commands);
        return getCp().parseCommand(commandString);
    }

    private void setActiveTurtles(List<Integer> activeTurtles) {
        this.getMyTurtleModel().clearCurrentActiveTurtles();
        this.getMyTurtleModel().setCurrentActiveTurtles(activeTurtles);
    }

    private void getTurtlesOfCondition(List<Integer> newActiveTurtles) {
        for (Integer currentTurtle : getMyTurtleModel().getAllTurtles().keySet()) {
            makeTurtleIndexActiveTurtle(currentTurtle);
            String[] condition = (String[]) getMyParams().get(0);
            String conditionString = String.join(" ", condition);
            double out = getCp().parseCommand(conditionString);
            if (out != 0) {
                newActiveTurtles.add(currentTurtle);
            }
        }
    }


    private void makeTurtleIndexActiveTurtle(Integer currentTurtle) {
        List<Integer> thisTurtleAsActive = new ArrayList<>();
        thisTurtleAsActive.add(currentTurtle);
        setActiveTurtles(thisTurtleAsActive);
    }
}
