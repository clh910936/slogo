package Commands;

import Models.ModelManager;

public class ClearScreen extends ZeroParamCommand {

    public ClearScreen(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        double dist = myTurtle.getDistToPoint(2000,2000);

        // TODO: talk to feroze
        myTurtle.setClearScreen();
        return dist;
    }
}
