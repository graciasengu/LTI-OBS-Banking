<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page import="banking.Accounts" %>
 <%@page import="banking.CustomerL"%> 
  <%@page import="banking.TransStore"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display</title>

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
	window.location = 'loginpage.jsp';
	</script>
<%
	
}
%>
	
<b>Your Login ID is : <%=pCustomerID %> </b> 	


Your new account balance is: $ <%=Accounts.getmBalance()%>
Transaction ID: <%=TransStore.get_transid()%>
Transaction amount: $ <%=TransStore.get_transactionamt()%>
Transaction type: <%=TransStore.get_transtype()%>


</body>
</html>