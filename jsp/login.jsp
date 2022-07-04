<%@ page session="true" import="java.util.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />

<jsp:setProperty name="validator" property="*" />
<jsp:setProperty name="user" property="*" />

<%
	boolean hasClicked=false;
	int temp = 0;
	validator.setContext(application);

	user.setRemoteHost(request.getRemoteHost() + " " +
		request.getRemoteAddr());
	if( request.getParameter("login") != null)
	{

			hasClicked=true;
			//System.err.println( validator.isValid() );
			//System.err.println( user );
			temp = validator.checkPassword();
			if( temp == 0 )
				response.sendRedirect("intro.jsp");
			else { 
				session.invalidate();
				//validator.invalidate();
			}
		
	} else  {
		validator.invalidate();	  
		//session.invalidate();
	}

%>

<html>
<head><title>Restaurant Preference Logger -- Login</title></head>
<body bgcolor="#DDDDDD">
<table border="1" align="center">
<tr><td>

<!-- Start of Inner Table -->
<form method="POST">

<table align="center">
<%

String err = request.getParameter("error");

if ( err != null)
{
	//System.err.println(err);
	String msg = null;
	if ( err.equals("COOKIE_ERROR") )
		msg = "Browser Must Suport Cookies";
	else if ( err.equals("LOGIN_REQ") )
		msg = "Login Required";
%>
<tr>
	<td colspan="2" align="center"><font color="red">
		<%= msg %>
	</font></td>
</tr>

<%
} 
	if (hasClicked){
	if (temp != 0) { 
		validator.setHasChecked( false );
		%>
<tr>
	<td colspan="2" align="center"><font color="red">
	<%
			//System.err.println(temp);
			if ( temp < 0 )
				%>Invalid Login<%
			if ( temp > 0 )
				%>DB ERROR.  CONTACT MARTY<%
	%>
	</font>
	</td>
</tr>

<% }} %>
<tr>
	<td align="right">Username:</td>
	<td align="left"><input type="text" name="username" size="20" maxlength="20"></td>
</tr>

<tr>
	<td align="right">Password:</td>
	<td align="left"><input type="password" name="password" size="20" maxlength="20"></td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" name="login" value="Login" />
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<a href="restPick.jsp">Pick Restaurant</a>
	</td>
</tr>
</table>
</form>
<!-- End of Inner Table -->

</td></tr>
</table>
</body>
</html>
