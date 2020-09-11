<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%> 
<%@ page import="banking.*"%> 
<%@ page import="java.lang.management.*"%> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requests for ATM Card Application</title>
<style>
		label{
  		display:block;
  		float:left;
  		width:220px;
   		}
</style>

<script type = "text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>

</head>
<body>

<div class="tab">

<button class="tablinks" onclick="RequestType(event, 'requests')" id="defaultOpen">Requests</button>
<button class="tablinks" onclick="RequestType(event, 'atmcard')">New ATM card requests</button>
<button class="tablinks" onclick="RequestType(event, 'newacct')" >New account applications</button>
<button class="tablinks" onclick="RequestType(event, 'closeacct')">Closing exisiting account requests</button>
</div>

<div id="requests" class="tabcontent">

<%
	int counter0=0;//from db
	int counter1=0;//from db
	int counter2=0;//from db
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");  //need to import java.sql.* to be able to do JDBC connection
	Statement stmt=con.createStatement();
	ResultSet rs=stmt.executeQuery("SELECT count(RequestType) FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND RequestType='Apply ATM Card'");
	while(rs.next())
	{
		counter0=rs.getInt(1); //getting count from db
	}
%>
	<p><label>ATM Card Requests:</label><%=counter0%></p>
	<%
	Statement stmt1=con.createStatement();
	ResultSet rs1=stmt1.executeQuery("SELECT count(RequestType) FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND (RequestType='Savings' OR RequestType='Current')");
	while(rs1.next())
	{
		counter1=rs1.getInt(1); //getting count from db
	}
	%>
	<p><label>New account applications:</label><%=counter1%></p>
		<%
	Statement stmt2=con.createStatement();
	ResultSet rs2=stmt2.executeQuery("SELECT count(RequestType) FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND RequestType='Close existing account'");
	while(rs2.next())
	{
		counter2=rs2.getInt(1); //getting count from db
	}
	%>
	<p><label>Close existing account applications:</label><%=counter2%></p>
	
</div>
<FORM Action="SUNewATMpath" method="post">
<div id="atmcard" class="tabcontent">
<%
	String mCustomerID=""; //initializing from db
	long mRequestNo=0; 
	String mRequestType="";
	
	Statement stmt3=con.createStatement();
	ResultSet rs3=stmt3.executeQuery("SELECT * FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND RequestType='Apply ATM Card'");
	while(rs3.next())
	{
		mCustomerID=rs3.getString(1); //from db columns
		mRequestNo=rs3.getLong(2);
		mRequestType=rs3.getString(3);
		%>
		<p><label>Customer ID:</label> <%=mCustomerID%></p><br> 
		<p><label>Request Number:</label> <%=mRequestNo%></p>
		<input type="hidden" name="reqnoapplyatm1" value="<%=mRequestNo%>">
		<br><p><label>Request Type:</label> <%=mRequestType%></p><br>
		<%
		long mAccountNo=0;
		Statement stmt6=con.createStatement();
		ResultSet rs6=stmt6.executeQuery("SELECT * FROM ATM WHERE cardstatus='Pending' AND customerid='"+mCustomerID+"'");
		while (rs6.next())
		{
			mAccountNo=rs6.getLong(3); 
			break;
		}
		%>
		<br><p><label>Account Number:</label> <%=mAccountNo%></p><br>
		<input type="hidden" name="accnoapplyatm" value="<%=mAccountNo%>">
		<BR><label><input type="radio" name="a" value="Approved">Approve</label>
		<label><input type="radio" name="a" value="Rejected">Reject</label>
		<BR><BR>
		Reason for rejection:<input type="text" name ="remarkbox1">
		<p><input type="submit" value="Submit"></p>
		<br>
		<%
	}
		%>
	
</div>
</FORM>

<FORM Action="SUNewAccpath" method="post">
<div id="newacct" class="tabcontent">

<%
	String mCustomerID1=""; //from db
	long mRequestNo1=0;
	String mRequestType1="";
	
	Statement stmt4=con.createStatement();
	ResultSet rs4=stmt4.executeQuery("SELECT * FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND (RequestType='Savings' OR RequestType='Current')");
	while(rs4.next())
	{
		mCustomerID1=rs4.getString(1); 
		mRequestNo1=rs4.getLong(2);
		mRequestType1=rs4.getString(3); //pulling from db columns
		%>
		<p><label>Customer ID:</label> <%=mCustomerID1%></p><br>
		<p><label>Request Number:</label> <%=mRequestNo1%></p>
		<input type="hidden" name="reqnonewacc" value="<%=mRequestNo1%>">
		<br><p><label>Request Type:</label> <%=mRequestType1%></p><br> 
		Please select one:
		<BR><label><input type="radio" name="a" value="Approved">Approve</label>
		<label><input type="radio" name="a" value="Rejected">Reject</label>
		<BR><BR>
		Reason for rejection:<input type="text" name ="remarkbox2">
		<p><input type="submit" value="Submit"></p>
	<%
	}
	%>
	
</div>
</FORM>
<FORM Action="SUCloseAccpath" method="post">
<div id="closeacct" class="tabcontent">

<%
	String mCustomerID2=""; //from db
	long mRequestNo2=0;
	String mRequestType2="";
	
	Statement stmt5= con.createStatement();
	ResultSet rs5=stmt5.executeQuery("SELECT * FROM Request WHERE AppStatus='Complete' AND RequestStatus='Pending' AND RequestType='Close existing account'");
	while(rs5.next())
	{
		mCustomerID2=rs5.getString(1); //from db columns
		mRequestNo2=rs5.getLong(2);
		mRequestType2=rs5.getString(3);
		%>
		<p><label>Customer ID:</label> <%=mCustomerID2%></p><br>
		<p><label>Request Number:</label> <%=mRequestNo2%></p>
		<input type="hidden" name="reqnocloseacc" value="<%=mRequestNo2%>">
		<br><p><label>Request Type:</label> <%=mRequestType2%></p><br>
		<%
		long mAccountNo2=0;
		Statement stmt7=con.createStatement();
		ResultSet rs7=stmt7.executeQuery("SELECT * FROM ATM WHERE cardstatus='Pending' AND customerid='"+mCustomerID2+"'");
		while (rs7.next())
		{
			mAccountNo2=rs7.getLong(3); 
			break;
		}
		%>
		<br><p><label>Account Number:</label> <%=mAccountNo2%></p><br>
		<input type="hidden" name="accnocloseacc" value="<%=mAccountNo2%>">
		<BR><label><input type="radio" name="a" value="Approved">Approve</label>
		<label><input type="radio" name="a" value="Rejected">Reject</label>
		<BR><BR>
		Reason for rejection:<input type="text" name ="remarkbox3">
		<p><input type="submit" value="Submit"></p>
		<br>
		<%
	}
		%>
		
</div>	
</FORM>

<script>
function RequestType(evt, reqType) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(reqType).style.display = "block";
    evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>

</body>
</html>