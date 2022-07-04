<%@ page session="true" import="marty.lunchProgram.DataHelper,marty.mailer.EmailBean" %>

<jsp:useBean id="validator" scope="session" class="marty.lunchProgram.Validator" />
<jsp:useBean id="user" scope="session" class="marty.lunchProgram.User" />
<jsp:useBean id="ebean" scope="session" class="marty.mailer.EmailBean" />

<jsp:setProperty name="ebean" property="*" />

<%
	if (user==null || ! validator.isValid() )
		response.sendRedirect("login.jsp");

	if( user.getUsername() == null ) 
		response.sendRedirect("login.jsp");

	if ( !(user.getUsername().equals("marty") ||
			  user.getUsername().equals("sprenkle")))
	{
		response.sendRedirect("forbidden.jsp");		
	}

	if ( request.getParameter("send") != null )
	{
		DataHelper dh = new DataHelper();
		if (dh.sendListMail(request.getParameter("from"),
			request.getParameter("subject"),
			request.getParameter("message") ) )
			response.sendRedirect("success.jsp");	
		 else response.sendRedirect("failure.jsp");
	}
%>

<html>
<head><title>Restaurant Preference Logger -- Send Users Email</title></head>
<body bgcolor="#DDDDDD">


<form>
<table align="center" cellspacing="10">
<tr>
   <td align="right"> From: </td>
   <td align="left"><input type="text" name="from"
value="Lunch Program <admin@server.domain>"> </td>
</tr>
<tr>
   <td align="right"> Message Subject: </td>
   <td align="left"><input type="text" name="subject" /> </td>
</tr>
<tr>
   <td align="center" colspan="2"> 
		Message Body:<br>
      <textarea cols="50" rows="10" wrap="HARD" name="message"></textarea>
   </td>
</tr>
<tr>
   <td colspan="2" align="center"><input type="submit" name="send" value="Send"></td>
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
