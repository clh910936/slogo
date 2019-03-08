package Main;

import API.IModelManager;
import FrontExternal.GUI;
import FrontInternal.Components.Console;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Main extends Application {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private TabPane myTabs;
    private Tab myPlusTab;
    private Console myConsole;
    private Map<Tab, IModelManager> myTabMap;
    private ResourceBundle myResources;
    private int myTabCount;

    @Override
    public void start(Stage stage) {
        initializeVariables();
        initializeStage(stage);

        makePlusTab();
        makeNewTab();

        myTabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if(t1.equals(myPlusTab)){
                    makeNewTab();
                }
            }
        });
        stage.show();
    }

    private void initializeStage(Stage stage) {
        Scene scene = new Scene(myTabs, HEIGHT, WIDTH);
        stage.setScene(scene);
        stage.setTitle(myResources.getString("title"));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    private void initializeVariables() {
        myResources = ResourceBundle.getBundle("Main");
        myConsole = new Console();
        myTabMap = new HashMap<>();
        myTabs = new TabPane();
        myTabCount = 0;
    }

    public static void main(String[] args){
        launch(args);
    }

    private void makePlusTab(){
        myPlusTab = new Tab();
        myPlusTab.setText("+");
    }


    private void makeNewTab(){
        myTabCount++;
        GUI display = new GUI(myConsole);
        myConsole.setModelManager(display.getModelManager());
        Tab tab = new Tab();
        tab.setText(myResources.getString("Tab") + myTabCount);
        tab.setContent(display.getPane());
        IModelManager manager = display.getModelManager();
        myTabMap.put(tab, manager);
        formatTabs(tab);
    }

    private void formatTabs(Tab tab) {
        myTabs.getTabs().add(tab);
        myTabs.getTabs().remove(myPlusTab);
        myTabs.getTabs().add(myPlusTab);
        myTabs.getSelectionModel().select(tab);
        myTabs.getSelectionModel().select(myTabCount-1);
    }

}

