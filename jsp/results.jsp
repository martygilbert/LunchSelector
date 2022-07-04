<%@ page session="true" import="java.util.*, marty.lunchProgram.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />

<%
	if( user == null || !validator.isValid())
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");
%>

<html>
<head><title>Restaurant Preference Logger -- Preference Selecter</title></head>
<body bgcolor="#DDDDDD">

<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

<BR><BR>

<table cellpadding="5" align="center" border="1">
<tr>
 <th colspan="2"><font size="+3">RESULTS</font></th>
<tr>
<tr>
	<td align="center" colspan="2">
		<font color="blue">Blue:</font> First Choice<BR>
		<font color="red">Red:</font> Second Choice<BR>
		<font color="green">Green:</font> Third Choice<BR>
	</td>
</tr>
<%
	ResultFinder rf = new ResultFinder();
	Object[] rests = rf.getResults();
	Restaurant temp;
	for (int i = 0; i < rests.length; i++)
	{
		temp = (Restaurant) rests[i];	
		
%>
<tr>
	<td align="right"><%= temp.getName() %></td>
	<td align="left">&nbsp;
	
	<font color="blue"><% for (int j = 0; j < temp.totalFirstVotes(); j++) { %>X<%	}%></font>
	<font color="red"><% for (int j = 0; j < temp.totalSecondVotes(); j++) { %>X<%	}%></font>
	<font color="green"><% for (int j = 0; j < temp.totalThirdVotes(); j++) { %>X<%	}%></font>

	</td>
</tr>
<%
}
%>

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
