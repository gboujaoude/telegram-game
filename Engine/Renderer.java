import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.sound.midi.SysexMessage;

public class Renderer implements IEngineInterface {
    private GraphicsContext _gc;
    private SceneGraph _scene;
    boolean once = true;

    public SceneGraph getScene()
    {
        return _scene;
    }

    public void init(GraphicsContext gc)
    {
        _scene = new SceneGraph();
        _gc = gc;
        //_gc.fillText("hello", 10, 10);
        _gc.setFill(Color.ANTIQUEWHITE);
        _gc.fillRect(0, 0,
                      Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
    }

    @Override
    public boolean update(double deltaSeconds) {
        ImageView background = _scene.getBackground();
        if (background != null)
        {
            _gc.drawImage(background.getImage(), 10, 10);
        }
        return true;
    }
}