package Commands;


import Models.ModelManager;

public class Sum extends NonTurtleCommand {
    public Sum(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return (double) myParams.get(0) + (double) myParams.get(1);
    }
}
