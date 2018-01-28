import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Generic button class which can be added and removed from the window
 */
public class UIButton {
    private int _x, _y;
    private Button _button;


    /**
     * Creates a new button
     * @param x x location on the screen
     * @param y y location on the screen
     */
    UIButton(int x, int y)
    {
        _x = x;
        _y = y;
        _button = new Button();
        _button.setLayoutX(_x);
        _button.setLayoutY(_y);
    }

    public String  toString()
    {
       return _button.toString();
    }
    public void setStyle(String style)
    {
        _button.setStyle(style);
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

    public void setGraphic(ImageView view) {_button.setGraphic(view);}

    public void setXY(int x, int y)
    {
        _x = x;
        _y = y;
        _button.setLayoutX(x);
        _button.setLayoutY(y);
    }

    public void setActionEvent(EventHandler<ActionEvent> ae)
    {
        _button.setOnAction(ae);
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
