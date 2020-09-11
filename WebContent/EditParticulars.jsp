<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@page import="java.sql.*"%>
<%@page import="banking.CustomerL"%>     
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Particulars</title>

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

<%
String mfirstname="",mlastname="",memail="",madd="";
int mcontactno;
		mfirstname=CustomerL.getmFirstName();
		mlastname=CustomerL.getmLastName();
		memail=CustomerL.getmEmail();
		madd=CustomerL.getmAddress();
		mcontactno=CustomerL.getmContactNo();

%>


<form action="EditParticulars" method="post">
	<label>First Name: </label><Input type="text" Name="ufirstname" value="<%=mfirstname%>"/><br><br>
	<label>Last Name: </label><Input type="text" Name="ulastname" value="<%=mlastname%>"/><br><br>
	<label>Address: </label><Input type="text" Name="uadd" value="<%=madd%>"/><br><br>
	<label>Email Address: </label><Input type="text" Name="uemailadd" value="<%=memail%>"/><br><br>
	<label>Contact No.: </label><Input type="text" Name="ucontactno" value="<%=mcontactno%>"/><br><br>
	<input type="hidden" name="uCustomerID" value="<%=mCustomerID%>">
	<Input Type="submit" value="SUBMIT"/>
</form>
	
</body>
</html>