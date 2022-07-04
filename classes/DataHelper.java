package marty.lunchProgram;

import java.sql.*;
import java.util.*;

import marty.util.*;

public class DataHelper
{
	public DataHelper(){}

	
	private Vector restList;
	private boolean hasGottenList=false;

	/**
	* @return vector of IdNameBean objects (name,id).  Will return
	* empty or null vector if select didn't work.
	*/
	public Vector getRestaurantList() 
	{
		if ( ! hasGottenList )
			getRestaurants();
		return restList;
	}

	private void getRestaurants() 
	{
		restList = new Vector();
		DBDataHandler db = new DBDataHandler("lunchProgram");
		ResultSet rset=null;
		try{
			db.initDB();
			rset = db.executeSelect( 
				"SELECT name,rest_id FROM restaurants ORDER BY name");
		
			while (rset.next())
			{
				//if ( rset.getInt(2) != 23 )//what the heck? Why 23?
				restList.add(
				new IdNameBean(rset.getString(1),String.valueOf(rset.getInt(2))));
			}
			hasGottenList=true;
		} catch (SQLException sql) {
			hasGottenList = false;
		} finally { 
			try{ db.closeDB(); rset.close();}
			catch	(SQLException sql){}
		}
	}

	public String nameLookup(String id)
	{
		if (! hashInit) initHashTable();
		return (String)idLookup.get(id);
	}
	private Hashtable restLookup;
	private Hashtable idLookup;
	private boolean hashInit=false;
	private void initHashTable()
	{
		restLookup = new Hashtable();
		idLookup = new Hashtable();

		restLookup.put("0","Error");
		if( !hasGottenList )
			getRestaurants();
		ListIterator li = restList.listIterator();
		IdNameBean id;
		while (li.hasNext())
		{
			id = (IdNameBean) li.next();		
			restLookup.put( id.getValue(), id.getName());
			idLookup.put( id.getName(), id.getValue());
		}
		hashInit=true;
	}

	public boolean updateChoices(User user)
	{
		if (user == null) return false;

		boolean worked=false;

		DBDataHandler db = new DBDataHandler("lunchProgram");
		try{
			if (! db.initDB() )
				throw new SQLException("Couldn't Initialize DB");
			
			if(!hashInit) initHashTable();

			String c1 = user.getChoice1();
			String c2 = user.getChoice2();
			String c3 = user.getChoice3();

			if ( 	(c1.equals(c2) || c1.equals(c3)) ||
			 		(c2.equals(c1) || c2.equals(c3)) ||
			 		(c3.equals(c1) || c3.equals(c2))	)
		 		return false;

			String mysel = 
				"UPDATE users SET choice1=" + c1 +
				", choice2 = " + c2 +
				", choice3 = " + c3 +
				" WHERE username = '" + user.getUsername() + "'";
			//System.err.println(mysel);
			db.executeSelect(mysel);
			
			worked = true;
			sendUpdateChoicesMail(user);
			System.err.println("Sent email to " + user.getUsername());
			System.err.println(Calendar.getInstance().getTime());
		} catch (SQLException sql) {
			worked = false;
		} finally { db.closeDB();	}
		return worked;
	}

	public int[] getMyChoiceIDs(String username)
	{
		int[] picks={0,0,0};

		DBDataHandler db = new DBDataHandler("lunchProgram");	
		ResultSet rset=null;
		try{
			if(!db.initDB())
				throw new SQLException("Couldn't Initialize DB");		
			rset = db.executeSelect(
				"SELECT choice1, choice2, choice3 FROM " +
				"users WHERE username='" + username + "'");
			if (rset == null)
				throw new SQLException("rset is null!!");
			if (rset.next()) {
				picks[0] = rset.getInt(1);
				picks[1] = rset.getInt(2);
				picks[2] = rset.getInt(3);
			} else {
				//Session expired.  No username valid.
				//throw new SQLException("bad username!  Shouldn't happen");
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			picks[0]=0;
			picks[1]=0;
			picks[2]=0;
		} finally { 

			try{	db.closeDB();	rset.close();	}
			catch	(SQLException sql){}
			return picks;
		}
	}

	public String[] getMyChoices(String username)
	{
		String[] picks = new String[3];

		if (!hashInit) initHashTable();
		
		int[] temp = getMyChoiceIDs(username);
		picks[0] = (String) restLookup.get(String.valueOf(temp[0]));
		picks[1] = (String) restLookup.get(String.valueOf(temp[1]));
		picks[2] = (String) restLookup.get(String.valueOf(temp[2]));

		return picks;
	}

	public boolean changeUserPassword(User user)
	{
		boolean good = false;
		DBDataHandler db = new DBDataHandler("lunchProgram");
		try{
			if(!db.initDB())
				throw new SQLException("Couldn't Initialize DB");		
			String select = 
				"UPDATE users SET password=password('" +
					user.getNewpassword() + "') WHERE "+
					"username='" + user.getUsername() + "'";
			db.executeSelect(select);
			good = true;		
			user.setPassword(user.getNewpassword().trim());
		} catch (SQLException s) { good = false;	s.printStackTrace(); 	}
		finally 	{	db.closeDB();	}
		return good;
	}

	public String[] getUserList()
	{
		String[] uList = null;
		DBDataHandler db = null;
		ResultSet rset = null;
		try{

			db = new DBDataHandler("lunchProgram");
			if (! db.initDB() )	return uList;

			String sel = 
				"SELECT count(*) FROM users";
			rset = db.executeSelect(sel);
			
			rset.next();
			int size = rset.getInt(1);
			uList = new String[size];

			sel = 
				"SELECT username FROM users ORDER BY username";
			rset = db.executeSelect(sel);
			if (rset == null) return null;

			size = 0;
			while(rset.next())
			{
				uList[size] = rset.getString(1);
				size++;		
			}
			
		}	catch(SQLException sql)	{
			sql.printStackTrace();
		}	finally	{
			db.closeDB();
			try{	rset.close();	}
			catch (SQLException sql){}
			return uList;
		}
	}

	public boolean addNewUser(String username, String password, String email) 
	{
		if (username == null ||
			password == null ||
			email == null)
			return false;

		DBDataHandler db = new DBDataHandler("lunchProgram");
		if(!db.initDB())	return false;
		String insert = 
			"INSERT INTO users SET"+
			" username='"+username+
			"' ,password=password('"+password+
			"') ,email='"+email+"'";
		db.executeSelect(insert);
		db.closeDB();
		return true;
	}

	public boolean addNewRestaurant(String restName) 
	{
		if (restName == null) return false;
		DBDataHandler db = new DBDataHandler("lunchProgram");
		if(!db.initDB())	return false;
		String insert = 
			"INSERT INTO restaurants SET"+
			" name='"+restName +"'";
		db.executeSelect(insert);
		db.closeDB();
		return true;
	}

	public void sendUpdateChoicesMail(User user)
	{
		DBDataHandler db = new DBDataHandler("lunchProgram");	
		ResultSet rset = null;
		String emailAddress = null;
		try{
			if(!db.initDB())	return ; //need to log error
			String sel =
				"SELECT email FROM users WHERE username='" +
					user.getUsername() + "'";
			rset = db.executeSelect(sel);

			if ( rset == null ) return ;//log error
			if ( !rset.next()	) return ; //log error

			emailAddress = rset.getString(1);			
            System.err.println("EmailAddress is : " + emailAddress);
		}	catch(SQLException sql)	{
			sql.printStackTrace();
			return ;//log error	
		}	finally {
			db.closeDB();
			try {	rset.close();	}
			catch (Exception e) {}
		}

		
		String FromField = "\"The Lunch Selector Program\"<lunch@servername.domain>";
        String ToField = emailAddress;
        String SubjectField = "Restaurant Preference Change";
        String messageBody = "Hi " + user.getUsername() + "!! \n\n" +
			"This message is just to notify you that your lunch "+
			"preferences \nwere changed by:\n\n"+
			"Host: \t" + user.getRemoteHost() + "\n" +
			"Time: \t" + Calendar.getInstance().getTime() +
			".  \n\nThe New choices are:\n\n"+
			"\t Choice 1:\t" + (String)restLookup.get(user.getChoice1()) +
			"\n\t Choice 2:\t" + (String)restLookup.get(user.getChoice2()) +
			"\n\t Choice 3:\t" + (String)restLookup.get(user.getChoice3()) +
			"\n\nLet me know if you received this message in error."+	
			"\n\nThe Lunch Selector Program\n" +
            "http://servername.domain/lunch/\n"+
			"admin@servername.domain\n\n";
        try{

            SMTP.sendTxt(FromField,ToField,"localhost",SubjectField,messageBody);

        } catch (Exception e){
            //log the error somewhere better
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
		//return m.sendMail();
	}

	public boolean sendListMail(String from, String subj, String msg)
	{
		DBDataHandler db = new DBDataHandler("lunchProgram");	
		ResultSet rset = null;
		ArrayList<EmailBean> beans;
		try{
			if(!db.initDB())	return false;//add error logging
			String sel = "SELECT email FROM users";
			rset = db.executeSelect(sel);

			if ( rset == null ) return false;//add error logging

            msg +="\n\nhttp://servername.domain/lunch/\n";

			EmailBean eb = new EmailBean();
			eb.setFrom(from);
			eb.setSubject(subj);
			eb.setMessage(msg);
			beans = new ArrayList<EmailBean>();
			while ( rset.next() )
			{
				eb.setTo(rset.getString(1));	
				beans.add( new EmailBean(eb) );
			}

            for (EmailBean b: beans){
                MailerThread m = new MailerThread(b);
                new Thread(m).start();
            }

            return true;
			
		}	catch(SQLException sql)	{
			sql.printStackTrace();
			return false;//add error logging	
		}	finally {
			db.closeDB();
			try {	rset.close();	}
			catch (Exception e) {}
		}
	}
}
