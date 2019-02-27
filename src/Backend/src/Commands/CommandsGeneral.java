package Commands;


import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import java.util.List;

public abstract class CommandsGeneral {
    protected TurtleModel myTurtle;
    protected List<Object> myParams;
    protected String myLanguage;

    public CommandsGeneral(String language) {
        myLanguage = language;
    }

    public abstract boolean isCommandReadyToExecute();
    public abstract double executeCommand() throws ClassCastException;
    public void addParameterToCommand(Object val) throws IllegalParametersException {
        if( val instanceof TurtleModel) {
            myTurtle = (TurtleModel) val;
            return;
        }
        myParams.add(val);
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }




}
