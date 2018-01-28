import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Represents the scene for the game
 */
public class SceneGraph {
    private ImageView _background = null;
    private TreeSet<Actor> _actors;

    SceneGraph()
    {
        _actors = new TreeSet<>(new Comparator<Actor>()
        {
            @Override
            public int compare(Actor a1, Actor a2) {
                if (a1.getDepth() < a2.getDepth()) return -1;
                else if (a1.getDepth() > a2.getDepth()) return 1;
                else return 0;
            }
        });
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
        _actors.add(actor);
    }

    public void removeActor(Actor actor)
    {
        _actors.remove(actor);
    }

    public void reorderActors()
    {
        TreeSet<Actor> set = new TreeSet<>(_actors);
        set.clear();
        set.addAll(_actors);
        _actors = set;
    }

    public TreeSet<Actor> getActors()
    {
        return _actors;
    }
}
