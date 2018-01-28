import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.sound.midi.SysexMessage;

public class Renderer implements IEngineInterface {
    private GraphicsContext _gc;
    private SceneGraph _scene;

    public SceneGraph getScene()
    {
        return _scene;
    }

    public void init(GraphicsContext gc)
    {
        _scene = new SceneGraph();
        _gc = gc;
        //_gc.fillText("hello", 10, 10);
        //_gc.setFill(Color.ANTIQUEWHITE);
        //_gc.fillRect(0, 0,
        //              Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
    }

    @Override
    public boolean update(double deltaSeconds) {
        _gc.setFill(Color.ANTIQUEWHITE);
        //_gc.clearRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
        _gc.fillRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
        ImageView background = _scene.getBackground();
        if (background != null)
        {
            _gc.drawImage(background.getImage(), 0, 0);
        }
        _scene.reorderActors();
        boolean mouseClickRegistered = Singleton.engine.getInputManager().isClicked();
        int[] point = Singleton.engine.getInputManager().getLastPointClicked();
        for (Actor a : _scene.getActors())
        {
            if (mouseClickRegistered)
            {
                int x = a.getX();
                int y = a.getY();
                int width = a.getWidth();
                int height = a.getHeight();
                if (point[0] >= x && point[0] < x + width && point[1] <= y && point[1] > y - height)
                {
                    a.onMouseClick();
                }
            }
            a.render(_gc);
        }
        return true;
    }
}