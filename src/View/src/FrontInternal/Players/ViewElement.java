package FrontInternal.Players;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public abstract class ViewElement extends VBox{
    public ViewElement() {
        setMinHeight(100);
        setMinWidth(100);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10, 10, 0, 0));
    }

    protected void makeLabels(String... labels) {
        for (String s: labels) {
            getChildren().add(new Label(s));
        }
    }

    public void addButton(String label, EventHandler<ActionEvent> e) {
        var b = new Button(label);
        b.setMinWidth(50);
        b.setOnAction(e);
        getChildren().add(b);
    }

}
