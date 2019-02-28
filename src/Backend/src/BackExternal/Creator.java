package BackExternal;

import Models.ModelManager;

public class Creator {
    private IModelManager modelManager;

    public Creator() {
        modelManager = new ModelManager();
    }

    public IModelManager getModelManager() {
        return modelManager;
    }
}
