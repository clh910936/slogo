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

import java.beans.EventHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Main extends Application {
    public static final String TITLE = "SLOGO";
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private TabPane myTabs;
    private Tab myPlusTab;
    private Console myConsole;
    private Map<Tab, IModelManager> myTabMap;
    private ResourceBundle myResources;
    private int myTabCount;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Start");
        myResources = ResourceBundle.getBundle("Main");
        myConsole = new Console();
        myTabMap = new HashMap<>();
        //var model = new model();
        // should pass model into this maybe
        myTabCount = 0;
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        myTabs = new TabPane();
        makePlusTab();
        System.out.printf("Make new tab called from start");
        makeNewTab();
        myTabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                if(t1.equals(myPlusTab)){
                    makeNewTab();
                }
            }
        });



        Scene scene = new Scene(myTabs, HEIGHT, WIDTH);
        stage.setScene(scene);


        stage.setTitle(TITLE);
        stage.show();



    }

    public static void main(String[] args){
        launch(args);
    }

    private void makePlusTab(){
        myPlusTab = new Tab();
        myPlusTab.setText("+");
        myPlusTab.setOnSelectionChanged(e -> handleTabChange());
    }

    private void handleTabChange() {
//        System.out.println(myTabs.getSelectionModel().getSelectedItem().getText());
//        if(myTabs.getSelectionModel().getSelectedItem().equals(myPlusTab)){
//
//        }
    }

    private void makeNewTab(){
        System.out.println("********************************");
        System.out.println("Make new Tab called");
        myTabCount++;
        GUI display = new GUI(myConsole);
        myConsole.setModelManager(display.getModelManager());
        Tab tab = new Tab();
        //tab.setText();
        myTabs.getSelectionModel().select(tab);
        tab.setText(myResources.getString("Tab") + myTabCount);

        tab.setContent(display.getPane());
        IModelManager manager = display.getModelManager();
        myTabMap.put(tab, manager);
        myTabs.getTabs().add(tab);
        myTabs.getTabs().remove(myPlusTab);
        myTabs.getTabs().add(myPlusTab);
        myTabs.getSelectionModel().select(myTabCount-1);
    }

}

