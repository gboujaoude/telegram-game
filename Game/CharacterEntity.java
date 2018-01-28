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
    private Text _text;
    private Color _color;

    CharacterEntity(char character, int size, double blurRadius)
    {
        _blur = new GaussianBlur();
        _blur.setRadius(blurRadius);
        _character = "" + character;
        _text = new Text();
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

    public char getCharacter()
    {
        return _character.charAt(0);
    }
    
    public void setCharacter(char c)
    {
        _character = "" + c;
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

    }

    @Override
    public void onMouseClick() {
    	//setBlurRadius(0);
    	//setCharacter('.');
    	GameManagerWrapper.gameManager.setInFocus(this);
    	
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
