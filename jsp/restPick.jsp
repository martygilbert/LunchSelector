<%@ page session="true" import="java.util.*, marty.lunchProgram.*" %>
<jsp:useBean id="resultFinder" scope="session" class="marty.lunchProgram.ResultFinder" />
<jsp:setProperty name="resultFinder" property="*" />

<%
	String rest = resultFinder.findWinner();	
%>

<html>
<head><title>Restaurant Preference Logger -- Restaurant Choice</title></head>
<body bgcolor="#DDDDDD">

<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>
<BR><BR>

<table border="1" align="center" cellpadding="10">
<tr>
	<th><font size="+3">Restaurant Selection</font></th>
</tr>
<tr>
	<td align="center"><font size="+2"><%= rest %></font></td>
</tr>
</table>


<BR><BR>
<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
