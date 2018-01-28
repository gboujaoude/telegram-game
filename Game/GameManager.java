import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Stack;

public class GameManager
{
    private int _level;
    private Stack<Sentence> _levelSentences = new Stack<Sentence>();
    private ArrayList<TextContainer> _levelTextContainers = new ArrayList<TextContainer>();
    private TextContainer _currentSentence;
    private Scramble scramble = new Scramble();

    GameManager()
    {
        _level = 1;
        _levelSentences = scramble.getter(_level);
        _currentSentence = new TextContainer();
        _currentSentence.setText( _levelSentences.pop().getScrambled(), 50,0,0);
    }





}
