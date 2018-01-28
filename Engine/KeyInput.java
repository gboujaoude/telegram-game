import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.util.Stack;
/**
 * Provides an easy way for handling keyboard input
 */
public class KeyInput implements IEngineInterface {

    private Stack<String> _keyBuffer = new Stack<String>();
    private boolean _isClicked = false;
    private int[] _lastPointClicked;

    KeyInput(Scene scene)
    {
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> updateLastClickedPoint(e));
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> _keyBuffer.push(e.getText()));
    }

    private void updateLastClickedPoint(MouseEvent e) {
        _isClicked = true;
        _lastPointClicked = new int[]{(int)e.getX(), (int)e.getY()};
    }

    public String getLastKey()
    {
        return _keyBuffer.empty() ? "" : _keyBuffer.pop();
    }

    public boolean isClicked()
    {
        return _isClicked;
    }

    public int[] getLastPointClicked()
    {
        _isClicked = false;
        return _lastPointClicked;
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }


}
