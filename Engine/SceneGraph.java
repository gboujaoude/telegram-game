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

    SceneGraph()
    {
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
