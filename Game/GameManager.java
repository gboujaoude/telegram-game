import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

import sun.util.resources.cldr.chr.CalendarData_chr_US;

import javafx.scene.image.ImageView;
import javax.xml.soap.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Stack;

public class GameManager
{
    private int _level;
    private Stack<Sentence> _levelSentences = new Stack<Sentence>();
    private ArrayList<TextContainer> _levelTextContainers = new ArrayList<TextContainer>();
    private TextContainer _currentSentence;
    private Scramble _scramble = new Scramble();
    private CharacterEntity _inFocus;
    private int _xOffset;
    private int _yOffset;

    GameManager()
    {
        _level = 0;
        _levelSentences = _scramble.getter(_level);
        _currentSentence = new TextContainer();
        _xOffset = 30;
        _yOffset = Singleton.engine.getWindow().getHeight()/4 * 2;
        applyBlur();
        setBackground();
        addButton(300,400,"img/buttonsAndSigns/StartButton.png",1,"Start");

    }

    void nextLevel()
    {
        _level++;
        _levelSentences = _scramble.getter(_level);
        setBackground();
        nextTelegram();

    }

    void nextTelegram()
    {
        _currentSentence.setText( _levelSentences.pop().getScrambled(), 50,_xOffset,_yOffset);
    }


    void applyBlur()
    {
        int size = _currentSentence.size();
        for(int i = 0; i < size; i++)
        {
            CharacterEntity ce = _currentSentence.getEntityAt(i);
            if(ce.getCharacter() == '?')
            {
                ce.setBlurRadius(20.0);
            }
        }
    }



    void setBackground()
    {
        switch (_level)
        {
            case 0:
                Singleton.engine.getScene().setBackground("file:img/backgrounds/InstructionsScreenNoButton.jpg");
                break;
            case 1:
                Singleton.engine.getScene().setBackground("file:img/telegrams/Telegram01.jpg");
                break;
            case 2:
                Singleton.engine.getScene().setBackground("file:img/telegrams/Telegram02.jpg");
                break;
            case 3:
                Singleton.engine.getScene().setBackground("file:img/telegrams/Telegram03.jpg");
                break;
            case 4:
                Singleton.engine.getScene().setBackground("file:img/backgrounds/GameOverBackground.jpg");
                break;
            case 5:
                Singleton.engine.getScene().setBackground("file:img/backgrounds/???");
                break;
            default:
                Singleton.engine.getScene().setBackground("file:img/telegrams/Telegram01.jpg");
                break;
        }
    }

    void setInFocus(CharacterEntity ce)
    {
        if (_inFocus != null) _inFocus.setFlashPeriod(0);
        _inFocus = ce;
    }

    CharacterEntity getInFocus()
    {
    	return _inFocus;
    }


    void addButton(int x, int y, String imagePath, int mode,String text)
    {

        UIButton button = new UIButton(x, y);

        try
        {
            FileInputStream input = new FileInputStream(imagePath);
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            button.setGraphic(imageView);

        }

        catch (Exception e)
        {
            System.out.println(e);
            button.setText(text);
        }
        switch(mode)
        {
            case 1: button.setActionEvent(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    nextLevel();
                    button.removeFromWindow();
                    System.out.println("The button was clicked");
                }
            });
            break;
            case 2: button.setActionEvent(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
//                    TODO next tele after check
                    System.out.println("The button was clicked1");
                }
            });
            break;
             default: button.setActionEvent(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
//                    TODO goback to main menu
                    System.out.println("The button was clicked2");
                }
            });
        }

        button.addToWindow();

    }

}
