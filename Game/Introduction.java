import javafx.scene.paint.Color;

/**
 * Plays a quick intro animation
 */
public class Introduction implements IEngineInterface {
    private boolean _isIntroDone = true;

    public void playIntro()
    {
        _isIntroDone = false;
        Singleton.engine.getScene().setClearColor(Color.BLACK);
    }

    public boolean isIntroDone()
    {
        return _isIntroDone;
    }

    @Override
    public boolean update(double deltaSeconds) {

        return true;
    }
}
