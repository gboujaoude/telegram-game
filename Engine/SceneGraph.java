import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents the scene for the game
 */
public class SceneGraph {
    private ImageView _background = null;
    private Color _fillColor = Color.ANTIQUEWHITE;
    private LinkedList<Actor> _actors = new LinkedList<>();
    private final ReentrantLock _lock = new ReentrantLock();
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

    public void setClearColor(Color color)
    {
        _fillColor = color;
    }

    /**
     * Reverts clear color to default
     */
    public void unsetClearColor()
    {
        _fillColor = Color.ANTIQUEWHITE;
    }

    public Color getClearColor()
    {
        return _fillColor;
    }

    /**
     * Sets the background to nothing
     */
    public void unsetBackground()
    {
        _background = null;
    }

    public ImageView getBackground()
    {
        return _background;
    }

    public void registerActor(Actor actor)
    {
        _lock.lock();
        if (!_actors.contains(actor)) {
            if (_actors.size() == 0)
            {
                _actors.add(actor);
                _lock.unlock();
                return;
            }
            int index = 0;
            boolean wasAdded = false;
            for (Actor a : _actors) {
                if (a.getDepth() < actor.getDepth()) {
                    _actors.add(index, actor);
                    wasAdded = true;
                    break;
                }
                ++index;
            }
            if (!wasAdded) _actors.add(actor);
        }
        _lock.unlock();
    }

    public void removeActor(Actor actor)
    {
        _lock.lock();
        _actors.remove(actor);
        _lock.unlock();
    }

    public void reorderActors()
    {
        _lock.lock();
        LinkedList<Actor> list = new LinkedList<Actor>(_actors);
        _actors.clear();
        for (Actor a : list) registerActor(a);
        _actors = list;
        _lock.unlock();
    }

    public LinkedList<Actor> getActors()
    {
        _lock.lock();
        LinkedList<Actor> actors = new LinkedList<>(_actors);
        _lock.unlock();
        return actors;
    }
}