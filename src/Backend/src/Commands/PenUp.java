package Commands;

import Models.ModelManager;
import Models.Turtle;

public class PenUp extends ZeroParamCommand {

    public PenUp(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        myTurtle.setPenUp();
        return 0;
    }
}
