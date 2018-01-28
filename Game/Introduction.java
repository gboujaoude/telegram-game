import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Plays a quick intro animation
 */
public class Introduction implements IEngineInterface {
    private boolean _isIntroDone = true;
    private ArrayList<String> _text;
    private TextContainer _textContainer = new TextContainer();
    private int _index;
    private double _currentSeconds = 0.0;
    private double _secondsPerText = 3.0;
    private boolean _isFadeOut = true;

    public void playIntro()
    {
        _isIntroDone = false;
        _isFadeOut = true;
        Singleton.engine.getScene().setClearColor(Color.BLACK);
        _text = new ArrayList<>();
        _text.add("Created by Ready At 5");
        _text.add("Powered by Transmission Engine");
        _index = 0;
        _textContainer.setText("Hello", 50, 10, 10);
        _currentSeconds = 0.0;
    }

    public boolean isIntroDone()
    {
        return _isIntroDone;
    }

    @Override
    public boolean update(double deltaSeconds) {
        if (_isIntroDone) return true;
        if (Singleton.engine.getScene().isFadeDone())
        {
            if (_index >= _text.size())
            {
                _isIntroDone = true;
                return true;
            }
            if (_isFadeOut) {
                int width = Singleton.engine.getWindow().getWidth();
                int height = Singleton.engine.getWindow().getHeight();
                _textContainer.setText(_text.get(_index), 30, width / 6, height / 3);
                for (int i =0; i < _textContainer.size(); ++i)
                {
                    _textContainer.getEntityAt(i).setColor(Color.ANTIQUEWHITE);
                }
                Singleton.engine.getScene().fadeIn(2.0);
                _isFadeOut = false;
                _currentSeconds = 0.0;
                ++_index;
            }
            else
            {
                _currentSeconds += deltaSeconds;
                if (_currentSeconds >= _secondsPerText)
                {
                    _currentSeconds = 0.0;
                    Singleton.engine.getScene().fadeOut(2.0);
                    _isFadeOut = true;
                }
            }
        }
        return true;
    }
}
