package Commands;

import Models.ModelManager;

public class ClearScreen extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    public ClearScreen(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.setClearScreen();
        myModelManager.createNewTurtleModel();
        return dist;
    }
}
