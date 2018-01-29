import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *  Represents a single character to be drawn on the screen
 */
public class CharacterEntity extends Actor {
    private GaussianBlur _blur;
    private String _character;
    private String _oldCharacter;
    private Text _text;
    private Color _color;
    private int _flashPeriod = 0; // 0 means no flash - 1 means flash every frame
    private long _frameCount = 0;
    private boolean _isOnFlash = false;

    CharacterEntity(char character, int size, double blurRadius)
    {
        _blur = new GaussianBlur();
        _blur.setRadius(blurRadius);
        _text = new Text();
        setCharacter(character);
        _text.setEffect(new DropShadow());
        _text.setEffect(_blur);
        _text.setText(_character);
        _color = Color.BLACK;
        setFontsize(size);
    }

    public void setFontsize(int size)
    {
        setWidthHeight(size, size);
        _text.setFont(Font.loadFont("resources/carbontype.ttf", size));
        if(_text.getFont() == null) {
            _text.setFont(new Font("Courier New", size));
        }
    }

    /**
     * Adds a blinking effect for this character
     * @param period 0 = no flash, 1 =  flash once per frame
     */
    public void setFlashPeriod(int period)
    {
        _flashPeriod = period;
    }

    public char getCharacter()
    {
        if (!_character.equals(_oldCharacter))
        {
            _character = _oldCharacter;
        }
        return _character.charAt(0);
    }
    
    public void setCharacter(char c)
    {
        _character = "" + c;
        _oldCharacter = "" + c;
        _text.setText(_character);
    }

    public void setColor(Color color)
    {
        _color = color;
    }

    public void setBlurRadius(double radius)
    {
        _blur.setRadius(radius);
    }

    @Override
    public void update(double deltaseconds) {
        ++_frameCount;
        if (_flashPeriod > 0 && (_frameCount % _flashPeriod) == 0)
        {
            if (_isOnFlash)
            {
                setCharacter(_oldCharacter.charAt(0));
                _isOnFlash = false;
            }
            else
            {
                _isOnFlash = true;
                char c = _character.charAt(0);
                setCharacter(' ');
                _oldCharacter = "" + c;
            }
        }
        else if (_flashPeriod == 0 && !_character.equals(_oldCharacter))
        {
            setCharacter(_oldCharacter.charAt(0));
        }
    }

    @Override
    public void onMouseClick() {
    	//setBlurRadius(0);
    	//setCharacter('.');
    	GameManagerWrapper.gameManager.setInFocus(this);
    	setFlashPeriod(5);
    }

    @Override
    public void render(GraphicsContext gc) {
        int fontSize = (int)_text.getFont().getSize();
        if (getWidth() != fontSize || getHeight() != fontSize)
        {
            setWidthHeight(fontSize, fontSize);
        }
        gc.setFill(_color);
        gc.setEffect(_blur);
        gc.setFont(_text.getFont());
        gc.fillText(_text.getText(), getX(), getY());
    }
}
