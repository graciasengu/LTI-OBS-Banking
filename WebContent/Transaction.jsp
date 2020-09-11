<%@ page import="java.sql.*"%> 
<%@page import="banking.DBconnect"%> 
<%@page import="banking.CustomerL"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Transaction</title>
<style>
		label{
  		display:block;
  		float:left;
  		width:200px;
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
	window.location = 'loginpage.jsp';
	</script>
<%
	
}
%>
	<b>Your Login ID is : <%=mCustomerID %> </b> <br><br>	
	

<h2>Transaction Page</h2>

<div class="tab">

<button class="tablinks" onclick="Transtype(event, 'transpage')" id="defaultOpen">Account Transactions</button>
<button class="tablinks" onclick="Transtype(event, 'deposit')">Deposit</button>
<button class="tablinks" onclick="Transtype(event, 'withdraw')" >Withdrawal</button>
<button class="tablinks" onclick="Transtype(event, 'transfer')">Transfer</button>
</div>

<div id="transpage" class="tabcontent">
<p>
Please choose transaction type.
</p>
</div>

<FORM Action="depositpath" method="post">
<div id="deposit" class="tabcontent">
  <h3>Deposit</h3>
  <p>
	<input type="hidden" name="ttype" value="deposit">
	<BR><label>Choose account:</label>
<%
	int mAccountNo;
	DBconnect d1= new DBconnect();
	Connection con=d1.callDB(); 
	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
	pstmt.setString(1,mCustomerID);
	ResultSet rs=pstmt.executeQuery();
%>
	
	<select name="uaccountno">
	<%
	while(rs.next())
	{
		mAccountNo=rs.getInt(2); 
	%>
	<option><%=mAccountNo%></option>
	
	<%
	}
	%>
	 </select>
	 
	 <BR> <BR> 
	 <label>Choose transaction location:</label>
	 <select name="utranslocation">
	 <option>Beulah's Convenience Store: 313 Orchard Road, #01-35/36 313@Somerset, 238895</option>
	 <option>Beulah's Convenience Store: 201 Victoria Street, #01-09 Bugis+, 188067</option>
	 </select>
	 
	 <BR> <BR> 
	 <label>Transaction Amount:</label><input type="text" name="utransactionamt" /><br>
	 <BR>
	 <input type="hidden" name="uCustomerID" value="<%=mCustomerID%>">
	 <input type="submit" value="Submit">
</p>
</div>
</FORM>

<FORM Action="withdrawpath" method="post">
<div id="withdraw" class="tabcontent">
  <h3>Withdraw</h3>
  <p>
  <input type="hidden" name="uttype" value="withdraw">
	<BR><label>Choose account:</label>
	<select name="uaccountno">
	<%
	pstmt = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
	pstmt.setString(1,mCustomerID);
	rs=pstmt.executeQuery();
	%>
	<%
	while(rs.next())
	{
		mAccountNo=rs.getInt(2); 
	%>
	<option><%=mAccountNo%></option>
	
	<%
	}
	%>
	 </select>
	 
	 <BR><BR>
	 <label>Choose transaction location:</label>
	 <select name="utranslocation">
	 <option>Beulah's Convenience Store: 313 Orchard Road, #01-35/36 313@Somerset, 238895</option>
	 <option>Beulah's Convenience Store: 201 Victoria Street, #01-09 Bugis+, 188067</option>
	 </select>
	 
	 <BR><BR>
	<label>Transaction Amount:</label><input type="text" name="utransactionamt" /><br>
	 <BR>
	<input type="hidden" name="uCustomerID" value="<%=mCustomerID%>">
	<input type="submit" value="Submit">
</p> 
</div>
</FORM>

<FORM Action="transferpath" method="post">
<div id="transfer" class="tabcontent">
  <h3>Transfer</h3>
  <p>
  	<input type="hidden" name="uttype" value="transfer">
	<BR><label>Choose account:</label>
	
	<select name="uaccountno">
	<%
	pstmt = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
	pstmt.setString(1,mCustomerID);
	rs=pstmt.executeQuery();
	%>
	<%
	while(rs.next())
	{
		mAccountNo=rs.getInt(2); 
	%>
	<option><%=mAccountNo%></option>
	
	<%
	}
	%>
	 </select>
	 <BR><BR>
	<label>Transaction Amount:</label><input type="text" name="utransactionamt" /><br><BR>
	<label>Recipient Account Number:</label><input type="text" name="urecipientaccountno"/><br>
	 <BR>
	<input type="hidden" name="uCustomerID" value="<%=mCustomerID%>">
	<input type="submit" value="Submit">
</p>
</div>
</FORM>

<script>
function Transtype(evt, transType) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(transType).style.display = "block";
    evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>

</body>
</html>