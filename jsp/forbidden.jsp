<%@ page session="true" import="java.util.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<jsp:setProperty name="validator" property="*" />
<jsp:setProperty name="user" property="*" />

<%
	if ( user == null || ! validator.isValid() )
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

%>

<html>
<head><title>Restaurant Preference Logger -- FORBIDDEN</title></head>
<body bgcolor="#DDDDDD">
<BR><BR>
<center>You are not Marty.  You are scum.  Die.</center>

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
