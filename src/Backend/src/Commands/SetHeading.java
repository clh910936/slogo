package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        double difference = myTurtle.getDegreesDifference((double) getMyParams().get(0));
        myTurtle.setHeadingAngle((double) getMyParams().get(0));
        return difference;
    }
}
