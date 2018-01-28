import java.util.ArrayList;

class Sentence
{
    public String original;
    public String scrambled;
    public ArrayList<Integer> indices = new ArrayList<>();

    public Sentence(String original, String scrambled)
    {
        this.original = original;
        this.scrambled = scrambled;
        addBlurredIndeces(scrambled);
    }

    public void addBlurredIndeces(String scrambled)
    {
    	for(int i = 0; i < scrambled.length(); i++)
    	{
    		if(scrambled.charAt(i) == '?'){
    			indices.add(i);
    			System.out.println(i);
    		}
    	}
    }
    
    public String getOriginal()
    {
        return this.original;
    }
    
    public String getScrambled()
    {
        return this.scrambled;
    }

    public ArrayList<Integer> getIndices()
    {
    	return indices;
    }
}