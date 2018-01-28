import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Represents the scene for the game
 */
public class SceneGraph {
    private ImageView _background = null;
    private LinkedList<Actor> _actors = new LinkedList<Actor>();
    private double _fadeIn = 0.0, _fadeOut = 0.0;
    private double _elapsedFadeSeconds = 0.0;
    private double _fadeInOriginal = 0.0, _fadeOutOriginal = 0.0;

    SceneGraph()
    {
    }

    public boolean isFadeDone()
    {
        return _fadeIn == 0.0 && _fadeOut == 0.0;
    }

    public void fadeIn(double numSeconds)
    {
        if (numSeconds < 0.0) throw new IllegalArgumentException("Negative seconds for fade in");
        _fadeIn = numSeconds;
        _elapsedFadeSeconds = 0.0;
        _fadeInOriginal = numSeconds;
    }

    public void fadeOut(double numSeconds)
    {
        if (numSeconds < 0.0) throw new IllegalArgumentException("Negative seconds for fade out");
        _fadeOut = numSeconds;
        _elapsedFadeSeconds = 0.0;
        _fadeOutOriginal = numSeconds;
    }

    public void fadeOutFadeIn(double numSeconds)
    {
        fadeOut(numSeconds);
        fadeIn(numSeconds);
    }

    public double getFadeIn()
    {
        return _fadeIn;
    }

    public double getFadeOut()
    {
        return _fadeOut;
    }

    public double getFadeInOriginal()
    {
        return _fadeInOriginal;
    }

    public double getFadeOutOriginal()
    {
        return _fadeOutOriginal;
    }

    public double getElapsedFadeSeconds()
    {
        return _elapsedFadeSeconds;
    }

    public void setElapsedFadeSeconds(double value)
    {
        _elapsedFadeSeconds = value;
    }

    public void setBackground(String image)
    {
        _background = new ImageView(new Image(image,
                Singleton.engine.getWindow().getWidth(),
                Singleton.engine.getWindow().getHeight(),
                false, true));
    }

    public ImageView getBackground()
    {
        return _background;
    }

    public void registerActor(Actor actor)
    {
        if (_actors.size() == 0) _actors.add(actor);
        int index = 0;
        boolean wasAdded = false;
        for (Actor a : _actors)
        {
            if (a.getDepth() < actor.getDepth())
            {
                _actors.add(index, actor);
                wasAdded = true;
                break;
            }
            ++index;
        }
        if (!wasAdded) _actors.add(actor);
    }

    public void removeActor(Actor actor)
    {
        _actors.remove(actor);
    }

    public void reorderActors()
    {
        LinkedList<Actor> list = new LinkedList<Actor>(_actors);
        _actors.clear();
        for (Actor a : list) registerActor(a);
        _actors = list;
    }

    public LinkedList<Actor> getActors()
    {
        return _actors;
    }
}