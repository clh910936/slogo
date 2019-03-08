package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class HideTurtle extends ZeroParamCommand {

    public HideTurtle(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();
        myTurtle.setHideTurtle();
        return 0;
    }
}
