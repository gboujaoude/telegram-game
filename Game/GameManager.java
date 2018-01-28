import sun.util.resources.cldr.chr.CalendarData_chr_US;

import javax.xml.soap.Text;
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
        UIButton button = new UIButton("Start", 50, 100);
        button.setStyle("-fx-background-image: url('file:img/buttonsAndSigns/StartButton.jpg')");
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



}
