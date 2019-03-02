package FrontInternal.Util;

import BackExternal.*;
import FrontInternal.Views.ErrorView;
import FrontInternal.Views.ViewAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class was created such that all of the times parse needs to be called,
 * whether by the user or a Front End programmer, it all funnels through the same
 * method. Any class that implements the ViewAPI can add itself to be updated
 * whenever parse is called. This ensures that every time parse is called, the Views
 * respond correctly.
 */
public class Operator {
    private IModelManager myManager;
    private List<ViewAPI> myViews;
    private static final String DEFAULT_LANGAUGE = "English";
    private ErrorView myErrorView;
    private ResourceBundle myErrorResources;
    private final int ERROR_HEIGHT = 100;

    /**
     *  Creates an instance of Operator and operator creates an isntance of Creator such that
     *  the backend is initialized
     */
    public Operator(){
        myViews = new ArrayList<>();
        Creator creator = new Creator();
        myManager = creator.getModelManager();
        myErrorView = new ErrorView(ERROR_HEIGHT);
        myErrorResources = ResourceBundle.getBundle("Errors");
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
        try{
            myManager.parseCommand(command, language);
            for(ViewAPI v : myViews){
                v.update();
            }
        }
        catch (IllegalCommandException e){
            myErrorView.displayError(myErrorResources.getString("COMMAND"));
        }
        catch (IllegalParametersException e){
            myErrorView.displayError(myErrorResources.getString("UNKOWN"));
        }

    }

    /**
     * Returns IModelManager
     * @return instance of IModelManager associated with the current display
     */
    public IModelManager getManager(){
        return myManager;
    }

    /**
     * Allows the operator to call showError() and for the Console
     * to actually display it
     * @return ErrorView which extends an HBox
     */
    public ErrorView getErrorPane(){
        return myErrorView;
    }
}
