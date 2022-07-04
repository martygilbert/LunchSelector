<%@ page session="true" import="java.util.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />

<%
	//System.err.println(user);
	if (	user == null || 
			validator == null || 
			! validator.isValid() )
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	//System.err.println(user);

	if ( !user.getUsername().equals("marty"))
		response.sendRedirect("forbidden.jsp");		
	
%>

<html>
<head><title>Restaurant Preference Logger -- Administrative Menu</title></head>
<body bgcolor="#DDDDDD">

<table cellpadding="10" align="center">
<tr>
	<td align="center">
		<a href="addUser.jsp">Add New User</a>
	</td>
</tr>

<tr>
	<td align="center">
		<a href="addRestaurant.jsp">Add New Restaurant</a>
	</td>
</tr>

<tr>
	<td align="center">
		<a href="sendMail.jsp">Send Users Email</a>
	</td>
</tr>

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
