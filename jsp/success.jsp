<%@ page session="true" import="java.util.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator"/>
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User"/>

<%
	if ( user == null || !validator.isValid())
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	
%>

<html>
<head><title>Restaurant Preference Logger -- Operation Successful</title></head>
<body bgcolor="#DDDDDD">

<center>Operation Performed Successfully</center>

<BR><BR><BR><BR>
<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
