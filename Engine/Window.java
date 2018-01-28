import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;
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
        ConsoleVariables cvars = Singleton.engine.getCVarManager();
        if (cvars.contains("fullscreen")) _isFullscreen = Boolean.parseBoolean(cvars.getVariable("fullscreen"));
        if (cvars.contains("width")) _width = Integer.parseInt(cvars.getVariable("width"));
        if (cvars.contains("height")) _height = Integer.parseInt(cvars.getVariable("height"));
        stage.setFullScreen(_isFullscreen);
        if (_isFullscreen)
        {
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            _width = (int)screenSize.getWidth();
            _height = (int)screenSize.getHeight();
        }
        stage.setResizable(false);
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
        if (_width != (int)_jfxScene.getWidth() || _height != (int)_jfxScene.getHeight())
        {
            _width = (int)_jfxScene.getWidth();
            _height = (int)_jfxScene.getHeight();
            _canvas.setWidth(_width);
            _canvas.setHeight(_height);
        }
        return true;
    }
}
