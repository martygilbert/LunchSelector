<%@ page session="true" import="java.util.*, marty.lunchProgram.DataHelper" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<jsp:setProperty name="user" property="*" />

<%
	if (user == null || !validator.isValid())
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	String error=null;
	if ( request.getParameter("change") != null )
	{
		String pw1 = user.getNewpassword();
		String pw2 = user.getNewpassword2();

		if (	pw1 == null || pw2 == null )
			error="Blank Password Invalid";		
		else {
			pw1 = pw1.trim();
			pw2 = pw2.trim();

			if (	pw1.length() == 0 || pw2.length() == 0)
				error="Blank Password Invalid";

			else if ( !pw1.equals(pw2) )
					error="Passwords Don't Match";
		}

		if (error==null)
		{
			DataHelper dh = new DataHelper();
			if(dh.changeUserPassword(user))
			{
				response.sendRedirect("success.jsp");		
			} else {
				error = "Error Changing Passwords.  Notify marty@cs.duke.edu";
			}
		}
	}//outer if
%>

<html>
<head><title>Restaurant Preference Logger -- Change User Password</title></head>
<br>
<body bgcolor="#DDDDDD">

<table align="center">

<form>

<%
	if (error != null)
	{%>
<tr>
	<td colspan="2"align="center"><font size="+2" color="red"><%= error %></font></td>
</tr>
<%	}
%>
<tr>
	<td align="right"> New Password:</td>
	<td align="left">
		<input type="password" size="20" maxlength="20" name="newpassword" />
	</td>
</tr>

<tr>
	<td align="right"> Re-type New Password:</td> 
	<td align="left">
		<input type="password" size="20" maxlength="20" name="newpassword2"/>
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="submit" name="change" value="Change Password"/>
	</td>
</tr>
</form>

</table>

<BR><BR><BR>
<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
