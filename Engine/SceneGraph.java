import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the scene for the game
 */
public class SceneGraph {
    private ImageView _background = null;

    public void setBackground(String image)
    {
        _background = new ImageView(new Image(image,
                                              Singleton.engine.getWindow().getWidth(),
                                              Singleton.engine.getWindow().getHeight(),
                                              false, true));
    }

    public ImageView getBackground()
    {
        return _background;
    }
}
