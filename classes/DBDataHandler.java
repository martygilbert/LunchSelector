package marty.lunchProgram;
//package marty.tracker;

import java.sql.*;



public class DBDataHandler
{

	private static String db1 ="jdbc:mysql://localhost/";
	private String db;
	private static final String user="thisdbuser";
	private static final String pwd="thisdbpassword";
	private Connection myCon;
	private Statement stmt;
	private ResultSet rset;

	public DBDataHandler(String dbName)
	{
		db = db1 + dbName;
	}

	/**
	public DBDataHandler(){
		db = db1 + Constants.DB_NAME;
	}
	*/

	public void closeDB()
	{
		try{
			myCon.close();
			rset.close();
			stmt.close();
		} catch (SQLException sqle) {
			//what can I do??
		} catch (Exception e)
		{
/*			System.err.println(
			"General DB Error Occurred When Trying to Close the DB");
*/
		}
	}
	
	public boolean initDB()
	{
		boolean good = false;
		try{

			//Load the jdbc-odbc bridge driver
			Class.forName("org.gjt.mm.mysql.Driver");

			//DriverManager.setLogStream(System.out);
			
			myCon = DriverManager.getConnection(db,user,pwd);
			//If we can't connect, the above would throw an exception

			//Check for warnings generated
			checkForWarning ( myCon.getWarnings() );


			good = true;
		} catch (SQLException ex) {

					// A SQLException was generated.  Catch it and
					// display the error information.  Note that there
					// could be multiple error objects chained
					// together

					System.err.println ("\n*** SQLException caught ***\n");

					while (ex != null) {
							  System.err.println ("SQLState: " +
							  ex.getSQLState ());
							  System.err.println ("Message:  " +
							  ex.getMessage ());
							  System.err.println ("Vendor:   " +
							  ex.getErrorCode ());
							  ex = ex.getNextException ();
							  System.err.println ("");
					}
					good = false;
		 }
		 catch (java.lang.Exception ex) {

					// Got some other type of exception.  Dump it.

					ex.printStackTrace ();
					good = false;
		 }
		 
		return good;
	}

	private static boolean checkForWarning (SQLWarning warn) throws SQLException
	{
		// If a SQLWarning object was given, display the
		// warning messages.  Note that there could be
		// multiple warnings chained together
		boolean rc = false;

		if (warn != null) 
		{
			System.out.println ("\n *** Warning ***\n");
			rc = true;
			while (warn != null) 
			{
				System.out.println ("SQLState: " + warn.getSQLState ());
				System.out.println ("Message:  " + warn.getMessage ());
				System.out.println ("Vendor:   " + warn.getErrorCode ());
				System.out.println (""); warn = warn.getNextWarning ();
			}
		}
		return rc;
	}
	public synchronized ResultSet executeInsert(String select){
		try{

			stmt = myCon.createStatement();
			//stmt.getGeneratedKeys();
			stmt.executeUpdate(select,1);
			rset = stmt.getGeneratedKeys();

			
		} catch (SQLException sqle) {

			sqle.printStackTrace();
			return null;
		} catch(Exception e){
			e.printStackTrace();	
		}
		return rset;
	}
	
	public synchronized ResultSet executeSelect(String select)
	{
		try{
			stmt = myCon.createStatement();
			rset = stmt.executeQuery(select);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		} catch(Exception e){
			e.printStackTrace();	
		}
		return rset;
	}
	
	public synchronized void execute(String query)
	{
		try{
			stmt = myCon.createStatement();
			stmt.execute(query);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return ;
		} catch(Exception e){
			e.printStackTrace();	
		}
	}

	public static boolean isSQLSafe(String s) {
		return s.equals(makeSQLSafe(s));
	}

	public static String makeSQLSafe(String s) {
		String ret = "";
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isLetterOrDigit(c) || c == ' ' || c == '.' || c == ','
					|| c == '-' || c == '_' || c == '+' || c == '/' || c == '@'
					|| c == '!' || c == '(' || c == ')' || c == ';' || c == ':')
				ret += c;
			else if (c == '\\' || c == '\'' || c == '\"')
				ret += "\\"+c;
			else
				System.err.println("Rejected character: \""+c+"\"");
		}
		return ret;
	}


	public static void main(String args[])
	{
		DBDataHandler dbdh = new DBDataHandler("truett2005");

		
		if(!dbdh.initDB())
		{
			System.err.println("Could Not Connect To DB.  Try Later");
			return;
		}
		
		try{

			String select = "SELECT * FROM course ORDER BY priority";

			ResultSet rset = dbdh.executeSelect(select); 

			if (rset == null) {System.out.println("DB ERROR.  "); return ;} 
			while(rset.next()){ 
				System.out.println(rset.getInt(1)); 
			} 
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
