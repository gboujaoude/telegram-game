import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Scramble
{
	String fileSource = "sentences.txt";
	static int difficulty = 6;
	static Random rand = new Random();
	static Random rand2 = new Random();

	static String test1 = "The core faction lashed out.";
	static String test2 = "They were heroes";
	static String test3 = "I dozed off in my bunk.";
	static String test4 = "I told her the shortest way.";
	static String test5 = "Our efforts are unfounded.";

	static Stack<Sentence> battle1 = new Stack<>();
	static Stack<Sentence> battle2 = new Stack<>();
	static Stack<Sentence> battle3 = new Stack<>();

	static String guess = null;

	public Scramble()
	{

		try
		{
			scrambleAll();

		} catch (Exception e)
		{
			System.out.printf(e.toString());
		}

	}

	private	void scrambleAll() throws IOException
	{
		FileReader fr = new FileReader("resources/battle1.txt");
		BufferedReader in = new BufferedReader(fr);
		String current;
		while ((current = in.readLine()) != null)
		{
			String scram = scramble(current);
			Sentence scramable = new Sentence(current,scram);
			battle1.push(scramable);

		}
		fr = new FileReader("resources/battle2.txt");
		in = new BufferedReader(fr);
		difficulty = 7;
		while ((current = in.readLine()) != null)
		{
			String scram = scramble2(current);
			Sentence scramable = new Sentence(current,scram);
			battle2.push(scramable);

		}
		fr = new FileReader("resources/battle3.txt");
		in = new BufferedReader(fr);
		difficulty = 4;
		while ((current = in.readLine()) != null)
		{
			String scram = scramble3(current);
			Sentence scramable = new Sentence(current,scram);
			battle3.push(scramable);

		}
	}
	private String scramble(String value)
	{
		String temp = "";

		for (int i = 0; i < value.length(); i++) {
			int blur = rand.nextInt(difficulty);
			if(Character.isLetter(value.charAt(i)) && blur == 0)
			{
				temp = temp + "?";
			}
			else
			{
				temp = temp + value.charAt(i);
			}

		}
		return temp;
	}

	private String scramble2(String value)
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
				if( blur2 < 6) randC = '?';
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

	private String scramble3(String value)
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
				if( blur2 < 3) randC = '?';
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

	public Stack<Sentence> getter(int phase)
	{
		switch (phase)
		{
			case 1: return battle1;
			case 2: return battle2;
			case 3: return battle3;
			default: return battle3;

		}


	}
//	public static  void main(String[] args)
//  {
//    Scramble s = new Scramble();
//
//    Stack<Sentence> stack1 = s.getter(3);
//    for (Sentence sen : stack1)
//    {
//      System.out.println("sen orig: " + sen.getOriginal());
//      System.out.println("sen scram: " + sen.getScrambled());
//
//    }
//  }
}