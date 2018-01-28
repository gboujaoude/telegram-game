class Sentence
{
    public String original;
    public String scrambled;

    public Sentence(String original, String scrambled)
    {
        this.original = original;
        this.scrambled = scrambled;
    }

    public String getOriginal()
    {
        return this.original;
    }
    public String getScrambled()
    {
        return this.scrambled;
    }

}