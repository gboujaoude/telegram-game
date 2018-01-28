import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This is a specialized actor which assumes you want
 * to render with an image(s).
 */
public abstract class SpriteActor extends Actor {
    private Image _texture;

    public void setImage(String imageFile, int width, int height)
    {
        _texture = new Image(imageFile, width, height, false, true);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setGlobalAlpha(1.0);
        gc.drawImage(_texture, getX(), getY());
    }
}
