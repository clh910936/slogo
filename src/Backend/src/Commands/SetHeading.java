package Commands;

import Models.ModelManager;
import Models.Turtle;

public class SetHeading extends OneParamCommand {

    public SetHeading(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        double difference = myTurtle.getDegreesDifference((double) myParams.get(0));
        myTurtle.setHeadingAngle((double) myParams.get(0));
        return difference;
    }
}
