package Commands;

import Models.ModelManager;
import Models.Turtle;

public class XCoordinate extends ZeroParamCommand {
    public XCoordinate(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();


        return myTurtle.getCurrentX();
    }
}
