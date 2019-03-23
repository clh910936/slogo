package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

import java.util.ArrayList;
import java.util.List;

public class AskWith extends TwoParamCommand {
    public AskWith(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {

        List<Integer> savedActiveTurtles = new ArrayList<>(this.getMyTurtleModel().getCurrentActiveTurtles());
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
        String[] condition = (String[]) getMyParams().get(0);
        String conditionString = String.join(" ", condition);
        for (Integer currentTurtle : getMyTurtleModel().getAllTurtles().keySet()) {
            makeTurtleIndexActiveTurtle(currentTurtle);
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
