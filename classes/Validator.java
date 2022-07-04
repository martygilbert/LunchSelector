package marty.lunchProgram;

import java.sql.*;
import javax.servlet.*;

import marty.util.DBDataHandler;

/**
* Static class used for checking login names, etc...
*/
public class Validator
{
	public Validator()
	{}

	public boolean isValid() 
	{
		return isValid;
	}

	private static ServletContext application;
	public static void setContext(ServletContext con)
	{	application = con;	}

	public void invalidate()
	{	
		isValid = false;	
		hasChecked=false;
		username="";
		password="";
	}

	/**
	* @return 0 if good < 0 if invalid login, >0 if DBError
	* @throws SQLException if DB cannot initialize
	*/
	public static int checkPassword(String username, String password ) 
	{
		if(username == null || password == null) return -1;
		DBDataHandler db = new DBDataHandler("lunchProgram");
		ResultSet rset=null;
		try{
			if (db.initDB()) {
				System.err.println("line 3");
				String select = 
					"SELECT count(*) FROM users WHERE username ='"+username + "'" +
					"AND password=password('" + password + "')";
	
				application.log("user: " +username + " logged in"); 
	
				rset = db.executeSelect(select);
	
				hasChecked=true;
	
				rset.next();
				int count  = rset.getInt(1);
	
				if (count == 1) { 
					isValid=true;	
					return 0; 
				} else { 
					return -1; 
				}

			} else {
				System.err.println("line 4");
				throw new SQLException("Couldn't Initialize DB");
			}
		} catch(SQLException sql){
			sql.printStackTrace();
			return 2;
		} finally {
			try{ db.closeDB();	rset.close();	}
			catch (SQLException sql) {}
		}

	}

	/**
	* @return 0 if good, -1 if invalid password, 1 if invalid username
	*/
	public int checkPassword() throws SQLException
	{ return Validator.checkPassword(username, password); }

	public void setHasChecked(boolean flag)
	{	hasChecked=flag;							}
	private static boolean hasChecked=false;
	private static boolean isValid=false;

	private String username;
	public void setUsername(String s) {	username=s;	}
	public String getUsername(String s) {	return username;	}

	private String password;
	public void setPassword(String s) {	password=s;	}
	public String getPassword(String s) {	return password;	}

}
