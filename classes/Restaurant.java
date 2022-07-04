package marty.lunchProgram;

public class Restaurant
{
	public Restaurant(){}
	public Restaurant(String restName)
	{	name = restName;	 }
	public Restaurant(Restaurant rest)
	{
		name = rest.getName();
		third = rest.getThird();
		second = rest.getSecond();
		first = rest.getFirst();
	}

	public String toString()
	{
		return (name + "\t" + totalVotes() + "\t" +
			totalFirstVotes() + "\t" +
			totalSecondVotes() + "\t" +
			totalThirdVotes() + "\t");
	}

	public int totalVotes()
	{
		return (totalFirstVotes() +
			totalSecondVotes() +
			totalThirdVotes());
	}

	public int totalFirstVotes()
	{
		return ( first * 3 );
	}

	public int totalSecondVotes()
	{
		return ( second * 2 );
	}

	public int totalThirdVotes()
	{
		return ( third );
	}
	
	public void increment(int pos)
	{
		if ( pos == 1)
			first += 1;	
		else if (pos == 2)
			second += 1;
		else if (pos == 3 )
			third += 1;
	}
	private String name;
	public void setName(String restName){	name=restName;	}
	public String getName(){	return name;	}

	private int third = 0;
	public void setThird(int i){	third = i;	}
	public int getThird(){	return third;	}

	private int second = 0;
	public void setSecond(int i){	second = i;	}
	public int getSecond(){	return second;	}

	private int first = 0;
	public void setFirst(int i){	first = i;	}
	public int getFirst(){	return first;	}
}
