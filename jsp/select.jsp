<%@ page session="true" import="java.util.*, marty.lunchProgram.*, marty.util.*" %>
<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<jsp:setProperty name="user" property="*" />

<%
	if(user == null || !validator.isValid())
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	String error=null;
	if( request.getParameter("submit") != null)
	{
		DataHelper dh = new DataHelper();
		boolean test = dh.updateChoices(user);
		if (test)
			response.sendRedirect("success.jsp");
		else
			error="Make Sure Your New Choices are Unique";
	}
	if (request.getParameter("keep") != null)
		response.sendRedirect("intro.jsp");
%>

<html>
<head><title>Restaurant Preference Logger -- Preference Selecter</title></head>
<body bgcolor="#DDDDDD">
<form>
<table align="center" border="1" cellpadding="5">

<tr>
	<th colspan="2">Current Selections</th>
</tr>

<%
	String[] temp = user.myChoices();
	for (int i = 0; i < 3; i++) {
	%>
<tr>
<td align="right">
	<%= i + 1%>. 
</td>
<td align="left">
	<%= temp[i] %>
</td>
</tr>
	<% } 
%>

<tr>
	<td colspan="2" align="center"> 
	<input type="submit" name="keep" value="Keep Current Choices" />
	</td>
</tr>
</table>
<br><BR>
<table align="center" border="1" cellpadding="5">

<%
	if (error != null) {%>
		
<tr>
	<td colspan="2"><font size="+2" color="red"><%= error %></font></td>
</tr>
	<%
	}

%>
<tr>
	<th colspan="2"><font size="+2">Change Selections</font></th>
</tr>

<tr>
	<th>Choice</th>
	<th>Weight</th>
</tr>

<tr>
	<td align="center">
		<b>First Choice:</b><br>
		<select name="choice1">
			<%
				int[] temp2 = user.myChoiceIDs();
				String choice1 = String.valueOf(temp2[0]);
				String choice2 = String.valueOf(temp2[1]);
				String choice3 = String.valueOf(temp2[2]);

				DataHelper dh = new DataHelper();
				Vector rests = dh.getRestaurantList();
				ListIterator li = rests.listIterator();
				IdNameBean id;
				while(li.hasNext())
				{
					id = (IdNameBean) li.next();
					%>
					<option value="<%=id.getValue()%>" <%= choice1.equals(id.getValue())?"selected":""%>>
						<%= id.getName()%>
					</option>
					<%
				}
				li = null;
			%>	
		</select>
	</td>
	<td align="center"> 3 </td>
</tr>

<tr>
	<td align="center">
		<b>Second Choice:</b><br>
		<select name="choice2">
			<%
				li = rests.listIterator();
				while(li.hasNext())
				{
					id = (IdNameBean) li.next();
					%>
					<option value="<%=id.getValue()%>" <%= choice2.equals(id.getValue())?"selected":""%>>
						<%= id.getName()%>
					</option>
					<%
				}
				li = null;
			%>	
		</select>
	</td>
	<td align="center"> 2 </td>
</tr>

<tr>
	<td align="center">
		<b>Third Choice:</b><br>
		<select name="choice3">
			<%
				li = rests.listIterator();
				while(li.hasNext())
				{
					id = (IdNameBean) li.next();
					%>
					<option value="<%=id.getValue()%>" <%= choice3.equals(id.getValue())?"selected":""%>>
						<%= id.getName()%>
					</option>
					<%
				}
				li = null;
			%>	
		</select>
	</td>
	<td align="center"> 1 </td>
</tr>

<tr>
	<td colspan="2" align="center"><input type="submit" name="submit" value="Submit New Choices" />
<tr>

</table>
</form>


<table align="center" cellpadding="10">
<tr>
	<th><a href="intro.jsp">Return to Main Menu</a></th>
	<th>&nbsp;</th>
	<th><a href="login.jsp">Logout</a></th>
</tr>
</table>

</body>
</html>
