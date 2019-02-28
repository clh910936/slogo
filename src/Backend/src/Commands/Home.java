package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Home extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public Home(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.updatePoints(STARTX,STARTY);
        myTurtle.setPenDown();
        return dist;
    }
}
