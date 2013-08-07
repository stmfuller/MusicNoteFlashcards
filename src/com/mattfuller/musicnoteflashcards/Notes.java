package com.mattfuller.musicnoteflashcards;


//translate notes into numbers so that it's easier to generate notes reandomly
public enum Notes
{
	c(0), cS(1), d(2), dS(3), e(4), f(5), fS(6),g(7), gS(8), a(9), aS(10), b(11);
	
	//translate the note names to have values.
	private final int num;
	Notes (int num)
	{
		this.num = num;
	}
	
	//returns the value of a given note.
	public int getValue()
	{
		return num;
	}
	
	//returns the notename given a value. works with the String.valueOf command to
	//do String comparisons if needbe.
	public static Notes getNote(int n)
	{
		for (Notes note : values())
		{
			if (note.num == n)
				return note;
		}
		throw new IllegalArgumentException(String.valueOf(n));
	}
	
	
}
