<%@ page session="true" import="java.util.*,marty.lunchProgram.DataHelper" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />

<%
	if (user == null || ! validator.isValid() )
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");
	
	if ( !user.getUsername().equals("marty") )
	{
		response.sendRedirect("forbidden.jsp");		
	}
	
	if ( request.getParameter("newRest") != null )
	{
		DataHelper dh = new DataHelper();
		boolean temp = dh.addNewRestaurant(request.getParameter("restName"));
		if (temp)
			response.sendRedirect("success.jsp");
		else
			response.sendRedirect("failure.jsp");
	}
	

%>

<html>
<head><title>Restaurant Preference Logger -- Add Restaurant</title></head>
<body bgcolor="#DDDDDD">

<form>
<table align="center" cellspacing="10">

<tr>
	<td align="right">Restaurant Name</td>
	<td align="left"><input type="text" name="restName" size="30" maxlength="30"/></td>
</tr>


<tr>
	<td align="center" colspan="2">
		<input type="submit" name="newRest" value="Add Restaurant"/>
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
