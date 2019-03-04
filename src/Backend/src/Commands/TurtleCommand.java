package Commands;

import Models.ModelManager;
import Models.Turtle;

public abstract class TurtleCommand extends CommandNode {

    protected Turtle myTurtle;

    public TurtleCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
        myTurtle = (Turtle) myTurtleModel.getCurrentTurtle();
    }


    public abstract double executeCommand();
}
