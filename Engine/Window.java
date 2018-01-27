import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Maybe we need this idk
 */
public class Window {
    private Stage _stage;
    private Canvas _canvas;
    private GraphicsContext _gc;
    private boolean _isFullscreen = false;
    private int _width = 128;
    private int _height = 128;
    private String _title = "";

    public void setTitle(String title)
    {
        _title = title;
    }

    public void setWidth(int width)
    {
        _width = width;
    }

    public void setHeight(int height)
    {
        _height = height;
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public void setFullscreen(boolean fullscreen)
    {
        _isFullscreen = fullscreen;
    }

    public GraphicsContext init(Stage stage)
    {
        stage.setTitle(_title);
        _stage = stage;
        Group root = new Group();
        _canvas = new Canvas(_width, _height);
        root.getChildren().add(_canvas);
        stage.setScene(new Scene(root));
        _gc = _canvas.getGraphicsContext2D();
        stage.show();
        return _gc;
    }
}
