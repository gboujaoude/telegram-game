import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Generic button class which can be added and removed from the window
 */
public class UIButton {
    private int _x, _y;
    private Button _button;

    public interface IButtonClicked
    {
        /**
         * Called when the button is clicked
         */
        void onButtonClicked();
    }

    /**
     * Creates a new button
     * @param x x location on the screen
     * @param y y location on the screen
     */
    UIButton(String text, int x, int y)
    {
        _x = x;
        _y = y;
        _button = new Button();
        _button.setLayoutX(_x);
        _button.setLayoutY(_y);
        setText(text);
    }

    public void setStyle(String style)
    {
        _button.setStyle(style);
    }

    public void setOnClick(IButtonClicked onclick)
    {
        EventHandler<MouseEvent> evt = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                onclick.onButtonClicked();
            }
        };
        _button.setOnMouseClicked(evt);
    }

    public void setWidthHeight(int width, int height)
    {
        _button.setPrefWidth(width);
        _button.setPrefHeight(height);
    }

    public void setText(String text)
    {
        _button.setText(text);
    }

    public void setXY(int x, int y)
    {
        _x = x;
        _y = y;
        _button.setLayoutX(x);
        _button.setLayoutY(y);
    }

    public void setDisabled(boolean value)
    {
        _button.setDisable(value);
    }

    public void addToWindow()
    {
        ((Group)Singleton.engine.getWindow().getJFXScene().getRoot()).getChildren().add(_button);
    }

    public void removeFromWindow()
    {
        ((Group)Singleton.engine.getWindow().getJFXScene().getRoot()).getChildren().remove(_button);
    }
}
