package Commands;

import Models.ModelManager;
import Models.Turtle;

public class Home extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public Home(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.updatePoints(STARTX,STARTY);
        myTurtle.setPenDown();
        return dist;
    }
}
