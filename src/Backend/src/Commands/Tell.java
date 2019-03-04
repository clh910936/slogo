package Commands;

import Models.ModelManager;

public class Tell extends OneParamCommand {

    public Tell(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        String[] activeTurtles = (String[]) myParams.get(0);
        for(String i : activeTurtles) {
            myTurtleModel.addCurrentActiveTurtles(Integer.parseInt(i));
        }
        return Double.parseDouble(activeTurtles[activeTurtles.length-1]);
    }
}
