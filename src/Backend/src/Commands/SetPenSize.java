package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class SetPenSize extends OneParamCommand {

    public SetPenSize(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle = this.myTurtleModel.getCurrentTurtle();
        myTurtle.setPensize(Double.valueOf(String.valueOf(myParams.get(0))));
        return Double.valueOf(String.valueOf(myParams.get(0)));
    }
}
