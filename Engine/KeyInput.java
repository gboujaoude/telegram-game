import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.Stack;
/**
 * Provides an easy way for handling keyboard input
 */
public class KeyInput implements IEngineInterface {

    private Stack<String> _keyBuffer;
    private boolean _isClicked;
    KeyInput(Scene scene)
    {
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> _isClicked = true);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> _keyBuffer.push(e.getText()));
    }
    public String getLastKey()
    {
        return _keyBuffer.empty() ? "" : _keyBuffer.pop();
    }

    public boolean isClicked()
    {
        return _isClicked;
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }


}
