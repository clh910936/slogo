package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetShape extends OneParamCommand {

    public SetShape(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws IllegalParametersException {
        Turtle myTurtle = this.myTurtleModel.getCurrentTurtle();
        myTurtle.setShapeIndex(Integer.valueOf(String.valueOf(myParams.get(0))));
        return myParams.get(0);
    }
}
