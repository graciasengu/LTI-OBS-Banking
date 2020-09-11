<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@page import="banking.DBconnect"%> 
<%@page import="banking.CustomerL"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Block ATM Card</title>
</head>
<script type = "text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>

<body>

<% 

String mCustomerID=CustomerL.getmcustomerID();

//if customer ID is not available it will automatically redirect to login page
if(mCustomerID == null) 
{%>
	<script>
	alert(" You need to Log In!");
	window.location = 'loginpage.jsp';
	</script>
<%
	
}
%>
	
<b>Your Login ID is : <%=mCustomerID %> </b> 	



<%
	int mAccountNo;
	DBconnect d1= new DBconnect();
	Connection con=d1.callDB();
	PreparedStatement pstmt = con.prepareStatement("SELECT accountno FROM Accounts WHERE customerid =?");
	pstmt.setString(1,mCustomerID);
	ResultSet rs=pstmt.executeQuery();
%>

<form action=BlockATM method="post" >
	<select name="uaccountNo">
	<%
	while(rs.next())
	{
		mAccountNo=rs.getInt(1); 
	%>
	<option><%=mAccountNo%></option>
	
	<%
	}
	%>
	</select><br><br>

 <input type="hidden" name="customerid" value="<%=mCustomerID%>">
 <input type="submit" value="BLOCK">
</form>
</body>
</html>