package FrontInternal.Views;

import API.IModelManager;

public class SavedStateView extends View {


    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     *
     * @param manager
     */
    public SavedStateView(IModelManager manager) {
        super(manager);
    }

    //TODO: write this method once getSavedStates is a thing
    @Override
    public void update() {
        this.clearLines();
        
    }
}
