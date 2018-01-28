import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.Stack;
/**
 * Provides an easy way for handling keyboard input
 */
public class KeyInput implements IEngineInterface {

    private Stack<String> _keyBuffer = new Stack<String>();
    private boolean _isClicked = false;
    private Point _lastPointClicked;

    KeyInput(Scene scene)
    {
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> updateLastClickedPoint(e));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> _keyBuffer.push(e.getText()));
    }

    private void updateLastClickedPoint(MouseEvent e) {_lastPointClicked = new Point(e.getX(), e.getY()); }

    public String getLastKey()
    {
        return _keyBuffer.empty() ? "" : _keyBuffer.pop();
    }

    public boolean isClicked()
    {
        return _isClicked;
    }

    public Point getLastPointClicked()
    {
        _isClicked = false;
        return _lastPointClicked;
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }


}
