<%@ page session="true" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<%

	if ( user == null || !validator.isValid()) {
		response.sendRedirect("login.jsp?error=LOGIN_REQ");
	} else if( user.getUsername() == null ) {
		response.sendRedirect("login.jsp?error=COOKIE_ERROR");
	}


%>
<html>
<head><title>Restaurant Preference Logger -- Main Menu</title></head>
<body bgcolor="#DDDDDD">

<table align="center" cellpadding="5" border="1">
<tr>
	<th><b>Main Menu</b>
</tr>

<tr>
	<td align="center"><a href="select.jsp">Set Restaurant Preferences</a></td>
</tr>

<tr>
	<td align="center"><a href="results.jsp">View Current Distribution of Preferences</a></td>
</tr>

<tr>
	<td align="center"><a href="selectAttending.jsp">Find Winner</a></td>
</tr>

<tr>
	<td align="center"><a href="changePassword.jsp">Change Password</a></td>
</tr>
<%
if (user.getUsername().equals("sprenkle")){%>
<tr>
	<td align="center"><a href="sendMail.jsp">Send Users Mail</a></td>
</tr>
<%}%>

<tr>
	<td align="center"><a href="adminMenu.jsp">Site Administration</a> <br>(Restricted)</td>
</tr>

</table>

<BR><BR><BR>
<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
