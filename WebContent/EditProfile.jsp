<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="banking.CustomerL"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
<script type = "text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>
</head>
<body>
<% 
String pCustomerID=CustomerL.getmcustomerID();

//if customer ID is not available it will automatically redirect to login page
if(pCustomerID == null) 
{%>
	<script>
	alert(" You need to Log In!");
	window.location = 'login.html';
	</script>
<%
}
%>
	<b>Your Login ID is : <%=pCustomerID %> </b> <br><br>
<A href="ChangePassword.jsp">Change Password </A><br><br>
<A href="EditParticulars.jsp">Edit Particulars</A>


</body>
</html>