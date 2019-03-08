package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class ShowTurtle extends ZeroParamCommand {

    public ShowTurtle(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();


        myTurtle.setShowTurtle();
        return 1;
    }
}
