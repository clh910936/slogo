package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

import java.util.ArrayList;
import java.util.List;

public class Tell extends OneParamCommand {

    public Tell(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        String[] activeTurtles = (String[]) getMyParams().get(0);
        List<Integer> activeTurtleIds = new ArrayList<>();
        for(String i : activeTurtles) {
            activeTurtleIds.add(Integer.parseInt(i));
        }
        getMyTurtleModel().setCurrentActiveTurtles(activeTurtleIds);
        return Double.parseDouble(activeTurtles[activeTurtles.length-1]);
    }
}
