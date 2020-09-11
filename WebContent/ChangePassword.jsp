<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="banking.CustomerL"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<style>
		label{
  		display:block;
  		float:left;
  		width:150px;
   		}
	
</style>
<script type = "text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>

</head>
<body>
<% 
String mCustomerID=CustomerL.getmcustomerID();
//if customer ID is not available it will automatically redirect to login page
if(mCustomerID == null) 
{%>
	<script>
	alert(" You need to Log In!");
	window.location = 'login.html';
	</script>
<%
}
%>
	<b>Your Login ID is : <%=mCustomerID %> </b> <br><br>

<form action="ChangePassword" method="post">
	<label>Old Password: </label><Input type="password" Name="uoldpassword"/>
	<br><br>
	<label>New Password: </label><Input type="password" Name="unewpassword"/><br><br>
	<label>Retype New Password: </label><Input type="password" Name="unewpasswordA"/><br><br>
	<input type="hidden" name="uCustomerID" value="<%=mCustomerID%>">
	<Input Type="submit" value="SUBMIT"/>
</form>

</body>
</html>