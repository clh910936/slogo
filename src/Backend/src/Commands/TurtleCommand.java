package Commands;

import Models.ModelManager;
import Models.Turtle;

public abstract class TurtleCommand extends CommandNode {

    protected Turtle myTurtle;

    public TurtleCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        myTurtle = (Turtle) myTurtleModel.getCurrentTurtle();
    }

    @Override
//    public double evaluate() {
//        double currentValue = 0.0;
//        for (int id : myTurtleModel.getCurrentActiveTurtles()) {
//
//            myTurtleModel.setCurrentTurtle(id);
//            myTurtle = (Turtle) myTurtleModel.getCurrentTurtle();
//
//            for (CommandNode child : myChildren) {
//                myParams.add(child.evaluate());
//            }
//            currentValue = executeCommand();
//            myParams.clear();
//        }
//        return currentValue;
//    }

    protected abstract double executeCommand();
}
