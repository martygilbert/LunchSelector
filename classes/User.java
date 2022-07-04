package marty.lunchProgram;

public class User
{
	public User(){}


	public String toString()
	{
		String temp = null;

		temp = "Username: " + username + "\n";
		temp += "Password: " + password + "\n";
		temp += "Remote Host: " + rhost + "\n";
		temp += "Choice1: " + choice1 + "\n";
		temp += "Choice2: " + choice2 + "\n";
		temp += "Choice3: " + choice3 + "\n";
		return temp;
	}

	public void reset()
	{
		username="";
		password="";
		choice1="";
		choice2="";
		choice3="";
	}
	public String[] myChoices()
	{ 
		DataHelper dh = new DataHelper();
		return dh.getMyChoices(username); 
	}
	public int[] myChoiceIDs()
	{
		DataHelper dh = new DataHelper();
		return dh.getMyChoiceIDs(username);
	}

	private String rhost;
	public void setRemoteHost(String s)	{	rhost = s;	}
	public String getRemoteHost()	{	return rhost;	}
	
	private String username;
	public void setUsername(String s) {	username=s;	}
	public String getUsername()	{	return username;	}

	private String password;
	public void setPassword(String s) { password=s;	}
	public String getPassword()	{ return password;	}

	private String choice1;
	public void setChoice1(String s)	{	choice1 = s;	}
	public String getChoice1()	{	return choice1;	}

	private String choice2;
	public void setChoice2(String s)	{	choice2 = s;	}
	public String getChoice2()	{	return choice2;	}

	private String choice3;
	public void setChoice3(String s)	{	choice3 = s;	}
	public String getChoice3()	{	return choice3;	}

	private String newpassword;
	public void setNewpassword(String s){	newpassword=s;	}
	public String getNewpassword(){	return newpassword;	}

	private String newpassword2;
	public void setNewpassword2(String s){	newpassword2=s;	}
	public String getNewpassword2(){	return newpassword2;	}
}
