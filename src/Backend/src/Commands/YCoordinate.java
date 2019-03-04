package Commands;

import Models.ModelManager;
import Models.ModelManager;
import Models.Turtle;

public class YCoordinate extends ZeroParamCommand {
    public YCoordinate (String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        return myTurtle.getCurrentY();
    }
}
