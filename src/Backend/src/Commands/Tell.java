package Commands;

import BackExternal.ModelManager;

import java.util.ArrayList;
import java.util.List;

public class Tell extends OneParamCommand {

    public Tell(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        String[] activeTurtles = (String[]) myParams.get(0);
        List<Integer> activeTurtleIds = new ArrayList<>();
        for(String i : activeTurtles) {
            activeTurtleIds.add(Integer.parseInt(i));
        }
        myTurtleModel.setCurrentActiveTurtles(activeTurtleIds);
        return Double.parseDouble(activeTurtles[activeTurtles.length-1]);
    }
}
