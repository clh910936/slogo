package Commands;

import BackExternal.ModelManager;

public class SetPalette extends FourParamCommand {

    public SetPalette(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        myPaletteModel.addPalette(Integer.valueOf(String.valueOf(myParams.get(0))), Integer.valueOf(String.valueOf(myParams.get(1))),
                Integer.valueOf(String.valueOf(myParams.get(2))),Integer.valueOf(String.valueOf(myParams.get(3))));
        return Integer.valueOf(String.valueOf(myParams.get(0)));
    }
}
