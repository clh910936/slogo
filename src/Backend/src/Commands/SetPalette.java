package Commands;

import BackExternal.ModelManager;
import Models.Turtle;

public class SetPalette extends FourParamCommand {

    public SetPalette(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {

        int index = (int) (double) Double.valueOf(String.valueOf(myParams.get(0)));
        int r = (int) (double) Double.valueOf(String.valueOf(myParams.get(1)));
        int g = (int) (double) Double.valueOf(String.valueOf(myParams.get(2)));
        int b = (int) (double) Double.valueOf(String.valueOf(myParams.get(3)));

        Turtle myTurtle = this.myTurtleModel.getCurrentTurtle();
        myTurtle.setPalette(index, r, g, b);
        myPaletteModel.addPalette(index, r, g, b);

        return index;
    }
}
