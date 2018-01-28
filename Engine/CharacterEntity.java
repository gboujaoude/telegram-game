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
public abstract class CharacterEntity extends Actor {
    private GaussianBlur _blur;
    private String _character;
    private Text _text;
    private Color _color;

    CharacterEntity(char character, double blurRadius)
    {
        _blur = new GaussianBlur();
        _blur.setRadius(blurRadius);
        _character = "" + character;
        _text = new Text();
        _text.setEffect(new DropShadow());
        _text.setEffect(_blur);
        _text.setText(_character);
        _color = Color.BLACK;
    }

    public void setFontsize(int size)
    {
        _text.setFont(Font.font("Verdana", FontWeight.BOLD, size));
    }

    public char getCharacter()
    {
        return _character.charAt(0);
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
    public void render(GraphicsContext gc) {
        //System.out.println("Rendering");
        gc.setFill(_color);
        gc.setEffect(_blur);
        gc.setFont(_text.getFont());
        gc.fillText(_text.getText(), getX(), getY());
    }
}
