package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetPalette extends FourParamCommand {

    public SetPalette(ModelManager modelManager
) {
        super(modelManager
);
    }
    @Override
    public Object executeCommand() throws ClassCastException {

        int index = Integer.parseInt(getMyParams().get(0).toString());
        int r = Integer.parseInt(getMyParams().get(1).toString());
        int g = Integer.parseInt(getMyParams().get(2).toString());
        int b = Integer.parseInt(getMyParams().get(3).toString());

        Turtle myTurtle = this.getMyTurtleModel().getCurrentTurtle();
        myTurtle.setPalette(index, r, g, b);
        getMyPaletteModel().addPalette(index, r, g, b);

        return index;
    }
}
