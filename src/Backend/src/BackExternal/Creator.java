package BackExternal;

import Models.ModelManager;

public class Creator {
    ModelManager modelManager;

    public Creator() {
        modelManager = new ModelManager();
    }

    public IModelManager getModelManger() {
        return modelManager;
    }
}