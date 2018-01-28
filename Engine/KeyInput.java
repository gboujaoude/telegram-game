import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Provides an easy way for handling keyboard input
 */
public class KeyInput implements IEngineInterface {

    private String _lastKey;
    private boolean _isClicked;
    KeyInput(Scene scene)
    {
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> _isClicked = true);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> _lastKey = e.getText());
    }
    public String getLastKey()
    {
        return _lastKey;
    }

    private boolean isClicked()
    {
        return _isClicked;
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }


}
