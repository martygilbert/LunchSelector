<%@ page session="true" import="java.util.*, marty.lunchProgram.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<jsp:useBean id="resultFinder" scope="session" class="marty.lunchProgram.ResultFinder" />
<jsp:setProperty name="resultFinder" property="*" />

<%
	if( user == null || !validator.isValid())
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");
	
	if( request.getParameter("getRestaurant") != null )
	{
		//resultFinder.executeLottery();
		response.sendRedirect("restPick.jsp");
	}

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


<form>
<table cellpadding="5" align="center" border="1">

<tr>
	<th>Attending?</th>
	<th>Username</th>
</tr>
<%
	DataHelper dh = new DataHelper();
	String[] list = dh.getUserList();
	if(list != null) {
		for (int i = 0; i < list.length; i++)
		{
	%>
	<tr>
		<td align="center">
			<input type="checkbox" name="userList" value="<%=list[i]%>"/>
		</td>
		<td align="center">
			<%= list[i] %>
		</td>
	</tr>
	<%
		}
	}%>
<tr>
	<td colspan="2" align="center">
		<input type="submit" name="getRestaurant" value="Get Restaurant" /><br>
		<font size="-1">
			(HINT: Just clicking 'Get Restaurant' is the same as
			selecting all users)
		</font>
	</td>
</tr>

</table>
</form>


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
