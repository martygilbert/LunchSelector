package marty.lunchProgram;

import java.util.Comparator;

public class RestaurantComparator implements Comparator
{
	public RestaurantComparator()
	{

	}

	public int compare (Object r1, Object r2)
	{
		Restaurant rest1 = (Restaurant) r1;
		Restaurant rest2 = (Restaurant) r2;
		if ( rest1.totalVotes() < rest2.totalVotes()	)
			return 1;
		else if ( rest1.totalVotes() > rest2.totalVotes()	)
			return -1;
		else 
			return 0;
	}
}
