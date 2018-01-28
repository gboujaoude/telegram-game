import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;

public class TextContainer
{
    private ArrayList<CharacterEntity> _characters = new ArrayList<>();

    /**
     *
     * @param text text to print on the screen
     * @param size size of the font
     * @param xstart where the pixel starts on the screen
     * @param ystart where the pixel starts on the screen
     */
    public void setText(String text, int size, int xstart, int ystart)
    {
        for (CharacterEntity c : _characters)
        {
            Singleton.engine.getScene().removeActor(c);
        }
        _characters.clear();
        int x = xstart;
        int y = ystart;
        Window window = Singleton.engine.getWindow();
        int width = window.getWidth();
        String[] elements = text.split(" ");
        for (int j = 0; j < elements.length; j++){
            text = elements[j];
            int length = size * text.length();

            // Kick words to the next line if needed
            if (length >= width || length + x >= width) {
                x = xstart;
                y += size;
            }

            for (int i = 0; i < text.length(); ++i) {
                CharacterEntity c = new CharacterEntity(text.charAt(i), size, 0.0);
                c.setXYDepth(x, y, 0);
                _characters.add(c);
                Singleton.engine.getScene().registerActor(c);
                x += size;
            }
            CharacterEntity c = new CharacterEntity(' ', size, 0.0);
            c.setXYDepth(x, y, 0);
            _characters.add(c);
            Singleton.engine.getScene().registerActor(c);
            x += size;
        }
    }

    public CharacterEntity getEntityAt(int index)
    {
        if (index >= _characters.size() || index < 0) throw new RuntimeException("Index out of range to TextContainer");
        return _characters.get(index);
    }

    public int size()
    {
        return _characters.size();
    }
}