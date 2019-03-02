package FrontInternal.Util;

import BackExternal.Creator;
import BackExternal.IModelManager;
import FrontInternal.ViewAPI;

import java.util.ArrayList;
import java.util.List;

public class Operator {
    IModelManager myManager;
    List<ViewAPI> myViews;
    String DEFAULT_LANGAUGE = "English";

    public Operator(){
        myViews = new ArrayList<>();
        Creator creator = new Creator();
        myManager = creator.getModelManager();
    }

    /**
     * Adds a View that will have update called
     * every time Operator.parse() is called
     * @param view concrete implementation of ViewAPI
     */
    public void addViewToUpdate(ViewAPI view){
        myViews.add(view);
    }

    /**
     * Sends the given command to the parser through the ModelManager.
     * Note: the language is set to a default such that
     * programmers can call this and pass a command without needing to specify
     * a language.
     * @param command String of the command to execute.
     */
    public void parse(String command){
        parse(command, DEFAULT_LANGAUGE);
    }


    /**
     * Sends the given command to the parser through the ModelManager.
     * @param command String of Command to execute
     * @param language String of Langauge to execute
     */
    public void parse(String command, String language){
        myManager.parseCommand(command, language);
        for(ViewAPI v : myViews){
            v.update();
        }
    }

    /**
     * Returns IModelManager
     * @return instance of IModelManager associated with the current display
     */
    public IModelManager getManager(){
        return myManager;
    }
}
