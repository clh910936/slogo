package Commands;

import Models.ModelManager;

public abstract class NonTurtleCommand extends CommandNode {

    public NonTurtleCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public double evaluate() {
        for (CommandNode child : myChildren) {
            myParams.add(child.evaluate());
        }
        return executeCommand();
    }

    protected abstract double executeCommand();
}
