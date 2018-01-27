
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Scramble 
{
	String fileSource = "";
	static int difficulty = 10;
	static Random rand = new Random();
	static Random rand2 = new Random();
	
	public static void main(String[] args)
	{
        String test1 = "The core faction lashed out.";
        String test2 = "They were heroes;";
        String test3 = "I dozed off in my bunk.";
        String test4 = "I told her the shortest way.";
        String test5 = "Our efforts are unfounded.";
        
        boolean flag = false;
        String temp = "";
        
        
        
        for (int i = 0; i < test1.length(); i++) {
        	int blur = rand.nextInt(difficulty);
        	int blur2 = rand2.nextInt(difficulty);
        	if(Character.isLetter(test1.charAt(i)) && blur == 0)
        	{
        		char randC = (char)(rand.nextInt(26) + 'a');
        		if(i == 0) randC = Character.toUpperCase(randC);
        		if(blur2 <= 1 && blur2 < 7) randC = '?';
        		temp = temp + randC;
        		flag = !flag;
        	}
        	else
        	{
        		temp = temp + test1.charAt(i);
        	}
        			
		}
        System.out.println(temp);
   	}


	private Map<String,Info> scrambleAll() throws IOException
  {
		Map<String, Info> myMap = new HashMap<String, Info>();
		BufferedReader in
						= new BufferedReader(new FileReader(fileSource));
		String current;
		while ((current = in.readLine()) != null)
		{
			String scram = scramble(current);
			Info scramInfo = new Info(scram);

			myMap.put(current, scramInfo);
			System.out.println(scramInfo.scramble);
		}
		return myMap;
	}
	private String scramble(String value)
	{
		boolean flag = false;
		String temp = "";



		for (int i = 0; i < value.length(); i++) {
			int blur = rand.nextInt(difficulty);
			int blur2 = rand2.nextInt(difficulty);
			if(Character.isLetter(value.charAt(i)) && blur == 0)
			{
				char randC = (char)(rand.nextInt(26) + 'a');
				if(i == 0) randC = Character.toUpperCase(randC);
				if(blur2 <= 1 && blur2 < 7) randC = '?';
				temp = temp + randC;
				flag = !flag;
			}
			else
			{
				temp = temp + value.charAt(i);
			}

		}
		return temp;
	}
	class Phrase
	{
		String arg;
		public Phrase(String arg)
		{
			this.arg = arg;
		}
	}
	
	class Info
	{
		public String scramble;
		public Info(String scramble)
		{
			this.scramble = scramble;
		}
	}
}
