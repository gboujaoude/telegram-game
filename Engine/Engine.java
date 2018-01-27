import javafx.application.Application;
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
    private boolean _isInitialized = false;

    public Window getWindow() {
        return _window;
    }

    public KeyInput getInputManager()
    {
        return _input;
    }

    @Override
    public void start(Stage stage) {
        if (_isInitialized) return;
        _isInitialized = true;
        _window = new Window();
        GraphicsContext gc = _window.init(stage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
