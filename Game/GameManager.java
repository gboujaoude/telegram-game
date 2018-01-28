import sun.util.resources.cldr.chr.CalendarData_chr_US;

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
        int xOffset = 30;
        int yOffset = Singleton.engine.getWindow().getHeight()/4 * 2;
        _currentSentence.setText( _levelSentences.pop().getScrambled(), 50,xOffset,yOffset);
        applyBlur();
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





}
