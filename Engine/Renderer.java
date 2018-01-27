import javafx.scene.canvas.GraphicsContext;

public class Renderer implements IEngineInterface {
    private GraphicsContext _gc;

    public void init(GraphicsContext gc)
    {
        gc = _gc;
        //gc.fillText("hello", 0, 0);
    }

    @Override
    public boolean update(double deltaSeconds) {
        return true;
    }
}