package FrontInternal.Players;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddElement extends ViewElement {
    private final String ADD_BUTTON = "add.png";
    public AddElement(EventHandler e) {
        setAlignment(Pos.CENTER);
        var b = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(ADD_BUTTON)));
        b.setOnMouseClicked(e);
        getChildren().add(b);

    }
}
