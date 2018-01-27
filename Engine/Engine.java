import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Core component of the game. Its main job is to
 * run the main game loop, initialize and destroy game
 * subsystems and to provide a clear interface for accessing
 * them.
 *
 * @author Justin Hall
 * @author Christian Seely
 * @author Mina Faltas
 */
public class Engine extends Application {
    private Window _window;
    private Renderer _renderer;
    private KeyInput _input;
    private ApplicationEntryPoint _application;
    private long _prevFrameTime;
    private boolean _isRunning = true;
    private boolean _isInitialized = false;

    public Window getWindow() {
        return _window;
    }

    public KeyInput getInputManager()
    {
        return _input;
    }

    public SceneGraph getScene()
    {
        return _renderer.getScene();
    }

    @Override
    public void start(Stage stage) {
        if (_isInitialized) return;
        Singleton.engine = this;
        _isInitialized = true;
        _prevFrameTime = System.currentTimeMillis();
        _window = new Window();
        GraphicsContext gc = _window.init(stage);
        _renderer = new Renderer();
        _renderer.init(gc);
        _input = new KeyInput();
        _application = new ApplicationEntryPoint();
        _application.init();
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                Singleton.engine.engineLoop();
            }
        }).start();
    }

    private void engineLoop()
    {
        while (_isRunning) {
            long currTime = System.currentTimeMillis();
            long elapsed = currTime - _prevFrameTime;
            if (elapsed < (1/15.0*1000.0)) continue;
            _prevFrameTime = currTime;
            double deltaSeconds = elapsed / 1000.0;
            _isRunning = _isRunning && _window.update(deltaSeconds);
            _isRunning = _isRunning && _renderer.update(deltaSeconds);
            _isRunning = _isRunning && _input.update(deltaSeconds);
            _isRunning = _isRunning && _application.update(deltaSeconds);
        }
        shutdown();
    }

    @Override
    public void stop(){
        _isRunning = false;
        _application.shutdown();
        System.exit(0);
    }

    private void shutdown()
    {
        _application.shutdown();
        Platform.exit();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
