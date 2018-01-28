import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javax.sound.midi.SysexMessage;

public class Renderer implements IEngineInterface {
    private GraphicsContext _gc;
    private SceneGraph _scene;
    private double _globalAlpha = 0.0;

    public SceneGraph getScene()
    {
        return _scene;
    }

    public void init(GraphicsContext gc)
    {
        _scene = new SceneGraph();
        _gc = gc;
        //_gc.fillText("hello", 10, 10);
        //_gc.setFill(Color.ANTIQUEWHITE);
        //_gc.fillRect(0, 0,
        //              Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
    }

    @Override
    public boolean update(double deltaSeconds) {
        _gc.setFill(_scene.getClearColor());
        _gc.setGlobalAlpha(1.0);
        //_gc.clearRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
        _gc.fillRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
        ImageView background = _scene.getBackground();
        if (background != null)
        {
            _gc.drawImage(background.getImage(), 0, 0);
        }
        _scene.reorderActors();
        boolean mouseClickRegistered = Singleton.engine.getInputManager().isClicked();
        int[] point = Singleton.engine.getInputManager().getLastPointClicked();
        for (Actor a : _scene.getActors())
        {
            if (mouseClickRegistered)
            {
                int x = a.getX();
                int y = a.getY();
                int width = a.getWidth();
                int height = a.getHeight();
                if (point[0] >= x && point[0] < x + width && point[1] <= y && point[1] > y - height)
                {
                    a.onMouseClick();
                }
            }
            a.update(deltaSeconds);
            a.render(_gc);
        }
        double fadeIn = _scene.getFadeIn();
        double fadeInOriginal = _scene.getFadeInOriginal();
        double fadeOut = _scene.getFadeOut();
        double fadeOutOriginal = _scene.getFadeOutOriginal();
        double fadeSeconds = _scene.getElapsedFadeSeconds();
        if (fadeOut > 0.0)
        {
            _globalAlpha = (fadeOut - fadeSeconds) / fadeOutOriginal;
            _globalAlpha = 1.0 - _globalAlpha;
            _scene.setElapsedFadeSeconds(fadeSeconds + deltaSeconds);
            if (_globalAlpha >= 1.0)
            {
                _scene.fadeOut(0.0);
                _scene.setElapsedFadeSeconds(0.0);
                _globalAlpha = 1.0;
            }
        }
        else if (fadeIn > 0.0) {
            _globalAlpha = (fadeIn - fadeSeconds) / fadeInOriginal;
            _scene.setElapsedFadeSeconds(fadeSeconds + deltaSeconds);
            if (_globalAlpha <= 0.0)
            {
                _scene.fadeIn(0.0);
                _scene.setElapsedFadeSeconds(0.0);
                _globalAlpha = 0.0;
            }
        }
        _gc.setGlobalAlpha(_globalAlpha);
        _gc.setFill(Color.BLACK);
        _gc.fillRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());

        /*
        _gc.setFill(Color.DARKGRAY);
        _gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        _gc.setGlobalAlpha(0.2);
        MotionBlur blur = new MotionBlur();
        blur.setRadius(35);
        blur.setAngle(45);
        _gc.setEffect(blur);
        _gc.fillRect(0, 0, Singleton.engine.getWindow().getWidth(), Singleton.engine.getWindow().getHeight());
        blur.setRadius(0);
        _gc.setEffect(blur);
        */
        return true;
    }
}