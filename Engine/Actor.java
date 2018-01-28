import javafx.scene.canvas.GraphicsContext;

public abstract class Actor {
    private int _x = 0, _y = 0; // location
    private int _width = 0, _height = 0;
    private int _depth = 0; // how far back it is

    /**
     * Allow the actor to update it if needs to
     *
     * @param deltaseconds change in seconds since the last frame
     */
    public abstract void update(double deltaseconds);

    /**
     * Triggered when this actor is clicked
     */
    public abstract void onMouseClick();

    /**
     * Called by the rendering module to draw
     */
    public abstract void render(GraphicsContext gc);

    public void setXYDepth(int x, int y, int depth)
    {
        _x = x; // This offset prevents it from being inside the window border for (0,0)
        _y = y;
        _depth = depth;
        y = (int)Singleton.engine.getWindow().getJFXScene().getY();
        y += _height / 2;
        _y += y;
    }

    public void setWidthHeight(int width, int height)
    {
        _width = width;
        _height = height;
        int y = (int)Singleton.engine.getWindow().getJFXScene().getY();
        y += _height / 2;
        _y += y;
    }

    public int getX()
    {
        return _x;
    }

    public int getY()
    {
        return _y;
    }

    public int getDepth()
    {
        return _depth;
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }
}
