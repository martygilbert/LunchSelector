<%@ page session="true" import="marty.lunchProgram.DataHelper" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />

<%
	if (user==null || ! validator.isValid() )
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	if ( !user.getUsername().equals("marty") )
	{
		response.sendRedirect("forbidden.jsp");		
	}

	if ( request.getParameter("newUser") != null )
	{
		DataHelper dh = new DataHelper();
		boolean temp = dh.addNewUser(
			request.getParameter("username"),
			request.getParameter("password"),
			request.getParameter("email"));

		if (temp)
			response.sendRedirect("success.jsp");
		else
			response.sendRedirect("failure.jsp");
	}
%>

<html>
<head><title>Restaurant Preference Logger -- Add New User</title></head>
<body bgcolor="#DDDDDD">


<form>
<table align="center" cellspacing="10">

<tr>
	<td align="left">Username:</td>
	<td align="center">
		<input type="text" name="username" size="20" maxlength="20" />
	</td>
</tr>
<tr>
	<td align="left">Password:</td>
	<td align="center">
		<input type="text" name="password" size="20" maxlength="20" />
	</td>
</tr>
<tr>
	<td align="left">Email Address:</td>
	<td align="center">
		<input type="text" name="email" size="20" maxlength="20" />
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="submit" name="newUser" value="Add User" />
	</td>
</tr>


</table>
</form>




<BR><BR><BR>
<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="adminMenu.jsp">Admin Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
