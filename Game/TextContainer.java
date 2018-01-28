import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;

public class TextContainer
{
    private ArrayList<CharacterEntity> _characters = new ArrayList<>();

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
        for (int i = 0; i < text.length(); ++i)
        {
            CharacterEntity c = new CharacterEntity(text.charAt(i), size, 0.0);
            c.setXYDepth(x, y, 0);
            _characters.add(c);
            Singleton.engine.getScene().registerActor(c);
            System.out.println(c.getX() + " " + c.getY());
            x += size;
            if (x >= width || x + size >= width)
            {
                x = 0;
                y += size;
            }
        }
    }

    public CharacterEntity getEntityAt(int index)
    {
        if (index >= _characters.size() || index < 0) throw new RuntimeException("Index out of range to TextContainer");
        return _characters.get(index);
    }
}
