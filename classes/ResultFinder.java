package marty.lunchProgram;

import java.util.*;
import java.sql.*;

import marty.util.*;

public class ResultFinder
{
	public ResultFinder()
	{}
	
	public String findWinner()
	{
		//System.err.println("userList size: " + userList.length);
		Object[] rests = getResults(userList);
		String result;
		if (userList == null)
			result = "Looking at ALL users, the choice is: ";
		else
			result = 
				"Looking at "+userList.length+" user(s), the choice is: ";
		//System.err.println("rests loaded: " +rests.length);

		int totalEntries = 0;
		Vector activeList = new Vector();
		Restaurant temp;
		for ( int i = 0; i < rests.length; i++ )
		{
			temp = (Restaurant) rests[i];
			//System.err.println(temp);

			totalEntries += temp.totalVotes();
			for (int j = 0; j < temp.totalVotes(); j++)
			{	activeList.add(temp.getName());	}
		}

		//System.err.println("totalEntries: " + totalEntries);
		//System.err.println("vector size: " + activeList.size());

		Random rd = new Random(System.currentTimeMillis());
		int rand = rd.nextInt( totalEntries );
		userList = null;

		return (result + (String) activeList.elementAt(rand));
	}


	/**
	*
	*	@return Array of Restaurant Objects
	*/
	private Object[] getResults(String[] uList)
	{
		//read DB
		DBDataHandler db = new DBDataHandler("lunchProgram");
		ResultSet rset = null;
		try{
			if (! db.initDB())
				throw new SQLException("Couldn't initialize DB");
			String select =
				"SELECT choice1, choice2, choice3 FROM users "; 

			if (uList != null){
				select += "WHERE ";
				for (int i = 0; i < uList.length; i++)
				{
					if (i == (uList.length -1))
						select += "username='" + uList[i] + "' ";
					else
						select += "username='" + uList[i] + "' OR ";
				}
			}

			//System.err.println("Select is : " +select);

			rset = db.executeSelect(select);
			if (rset == null) {rset.close(); return null;}

			//Load hash w/Restaurant objects
			initRestHash();
			Restaurant temp = null;
			while ( rset.next() )
			{
				for (int i = 1; i < 4; i++ )
				{
					temp = (Restaurant) restHash.get(new String(
						String.valueOf(rset.getInt(i))));	
					if (temp != null)
					{ temp.increment(i); }
					else
					{	
						//This will happen when  rest="Not Selected";
						//...I think..
						//System.err.println("No increment..Null value"); 
					}
				}
			}
			
			//Calculate top 5
			Object[] orderedList = restHash.values().toArray();
			Arrays.sort( orderedList, new RestaurantComparator() );
			return orderedList;
		}	catch (SQLException sql)	{
			sql.printStackTrace();
			return null;
		} finally {
			try{ 	db.closeDB(); rset.close();	}
			catch (SQLException s) {}
		}
	
	}

	public Object[] getResults() 
	{ return getResults(null); }
	
	private void initRestHash()
	{
		//get restaurant list.
		//make into restaurant objects
		//put in a hashtable keyed by id
		DataHelper dh = new DataHelper();
		Vector temp = dh.getRestaurantList();
		ListIterator li = temp.listIterator();
		IdNameBean id;
		while (li.hasNext())
		{
			id = (IdNameBean) li.next();
			restHash.put (id.getValue(), new Restaurant(id.getName()));
		}
	}
	private Hashtable restHash = new Hashtable();
	//private Vector restaurants;

	private String[] userList = null;
	public void setUserList(String s[]){	userList = s;	}
	public String[] getUserList()	{	return userList;	}
}
