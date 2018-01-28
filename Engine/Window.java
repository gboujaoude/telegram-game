import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Maybe we need this idk
 */
public class Window implements IEngineInterface {
    private Stage _stage;
    private Canvas _canvas;
    private Scene _jfxScene;
    private GraphicsContext _gc;
    private boolean _isFullscreen = false;
    private int _width = 1024;
    private int _height = 768;
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

    public Scene getJFXScene()
    {
        return _jfxScene;
    }

    public GraphicsContext init(Stage stage)
    {
        stage.setTitle(_title);
        _stage = stage;
        Group root = new Group();
        _canvas = new Canvas(_width, _height);
        root.getChildren().add(_canvas);
        _jfxScene = new Scene(root);
        stage.setScene(_jfxScene);
        stage.show();
        _gc = _canvas.getGraphicsContext2D();
        return _gc;
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }
}
