import javafx.scene.canvas.GraphicsContext;

public abstract class Actor {
    private int _x, _y; // location
    private int _width, _height;
    private int _depth; // how far back it is

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
        _x = x;
        _y = y;
        _depth = depth;
    }

    public void setWidthHeight(int width, int height)
    {
        _width = width;
        _height = height;
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
