package Commands;

import Models.ModelManager;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode extends SyntaxNode {

    protected List<Object> myParams;
    protected List<CommandNode> myChildren;
    protected int myMaxParams;

    public CommandNode(String language, ModelManager modelManager) {
        super(language, modelManager);
        myParams = new ArrayList<>();
        myChildren = new ArrayList<>();
    }

    public void addChild(CommandNode node) {
        myChildren.add(node);
    }

    @Override
    public boolean isReadyToExecute() {
        return myChildren.size() == myMaxParams;
    }
}

