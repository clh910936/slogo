package Commands;


import Models.ModelManager;

public class Sum extends TwoParamCommand {
    public Sum (String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException  {
        return (double) input1 + (double) input2;
    }
}
