
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="java.sql.*" %>
<%@ page import="banking.DBconnect" %>
<%@page import="banking.CustomerL"%>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banking Account Menu</title>
<style>
	label{
  		display:block;
  		float:left;
  		width:125px;
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
String pCustomerID=CustomerL.getmcustomerID();
String pCustomerFirstName=CustomerL.getmFirstName();
String pCustomerLastName=CustomerL.getmLastName();
Date pCustomerDOB=CustomerL.getmDOB();
int pCustomerContactNo=CustomerL.getmContactNo();
String pCustomerAddress=CustomerL.getmAddress();
String pCustomerEmail=CustomerL.getmEmail();

//if customer ID is not available it will automatically redirect to login page
if(pCustomerID == null) 
{%>
	<script>
	alert(" You need to Log In!");
	window.location = 'CustLogin.html';
	</script>
<%
	
}
%>
	<b>Your Login ID is : <%=pCustomerID %> </b> <br><br>	

<%--establishing the tabs--%>

<div class="tab">
  <button class="tablinks" onclick="BankingAccTab(event, 'View Accounts')" id="defaultOpen" >View Accounts</button>
  <button class="tablinks" onclick="BankingAccTab(event, 'Open New Acccounts')">Open New Accounts</button>
  <button class="tablinks" onclick="BankingAccTab(event, 'Close Account')">Close Accounts</button>
</div>


<%--View Tab--%>

<div id="View Accounts" class="tabcontent">
		
		 <H3>Transaction record!</H3>
		 <form action="TransactionResult.jsp" method="post"target="_blank">

			<table style="width:100%">
				<tr>
					<div style="display:block;">
 					Start Date:
        
			        	<select name="udd">
			        
			            <option value="01"> 1</option>
			            <option value="02">2</option>
			            <option value="03">3</option>
			            <option value="04">4</option>
			            <option value="05">5</option>
			            <option value="06">6</option>
			            <option value="07">7</option>
			            <option value="08">8</option>
			            <option value="09">9</option>
			            <option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			            <option value="13">13</option>
			            <option value="14">14</option>
			            <option value="15">15</option>
			            <option value="16">16</option>
			            <option value="17">17</option>
			            <option value="18">18</option>
			            <option value="19">19</option>
			            <option value="20">20</option>
			    	    <option value="21">21</option>
			            <option value="22">22</option>
			            <option value="23">23</option>
			            <option value="24">24</option>
			            <option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
			        	</select>
			        	
			       		<select name="umm">
			            <option value="01">1</option>
			            <option value="02">2</option>
			            <option value="03">3</option>
			            <option value="04">4</option>
			            <option value="05">5</option>
			            <option value="06">6</option>
			            <option value="07">7</option>
			            <option value="08">8</option>
			            <option value="09">9</option>
			            <option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			       		</select>
			       		
			            <select name="uyy">
			            <option value="2000">2000</option>
			            <option value="2001">2001</option>
			            <option value="2002">2002</option>
			            <option value="2003">2003</option>
			            <option value="2004">2004</option>
			            <option value="2005">2005</option>
			            <option value="2006">2006</option>
			            <option value="2007">2007</option>
			            <option value="2008">2008</option>
			            <option value="2009">2009</option>
			            <option value="2010">2010</option>
			            <option value="2011">2011</option>
			            <option value="2012">2012</option>
			            <option value="2013">2013</option>
			            <option value="2014">2014</option>
			            <option value="2015">2015</option>
			            <option value="2016">2016</option>
			            <option value="2017">2017</option>
			            </select>
        				<br><br><br>
          	 			End Date:
        				<select name="uedd">
        
			            <option value="01"> 1</option>
			            <option value="02">2</option>
			            <option value="03">3</option>
			            <option value="04">4</option>
			            <option value="05">5</option>
			            <option value="06">6</option>
			            <option value="07">7</option>
			            <option value="08">8</option>
			            <option value="09">9</option>
			            <option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			            <option value="13">13</option>
			            <option value="14">14</option>
			            <option value="15">15</option>
			            <option value="16">16</option>
			            <option value="17">17</option>
			            <option value="18">18</option>
			            <option value="19">19</option>
			            <option value="20">20</option>
			    		<option value="21">21</option>
			            <option value="22">22</option>
			            <option value="23">23</option>
			            <option value="24">24</option>
			            <option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
			       	    </select>
			        	<select name="uemm">
			            <option value="01">1</option>
			            <option value="02">2</option>
			            <option value="03">3</option>
			            <option value="04">4</option>
			            <option value="05">5</option>
			            <option value="06">6</option>
			            <option value="07">7</option>
			            <option value="08">8</option>
			            <option value="09">9</option>
			            <option value="10">10</option>
			            <option value="11">11</option>
			            <option value="12">12</option>
			       		</select>
			       		<select name="ueyy">
			            <option value="2000">2000</option>
			            <option value="2001">2001</option>
			            <option value="2002">2002</option>
			            <option value="2003">2003</option>
			            <option value="2004">2004</option>
			            <option value="2005">2005</option>
			            <option value="2006">2006</option>
			            <option value="2007">2007</option>
			            <option value="2008">2008</option>
			            <option value="2009">2009</option>
			            <option value="2010">2010</option>
			            <option value="2011">2011</option>
			            <option value="2012">2012</option>
			            <option value="2013">2013</option>
			            <option value="2014">2014</option>
			            <option value="2015">2015</option>
			            <option value="2016">2016</option>
			            <option value="2017">2017</option>
			        	</select>
			        	<br><br>
			      </div>
        		</tr>
       		 </table>
         
			<div style="display:block;">
				<%
				int mAccountNo;
				DBconnect d1 = new DBconnect();
				Connection con = d1.callDB(); 
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
				pstmt.setString(1,pCustomerID);
				ResultSet rs=pstmt.executeQuery();
				%>
				
				Account No <select name="Account_Number">
				<%
				while(rs.next())
				{
					mAccountNo=rs.getInt(2);
				%>
				<option><%=mAccountNo%></option>
				
				<%
				}
				%>
				
				</select><br><br>
			</div>
			<div><input type="submit" value="VIEW"/></div>
		 	
	</form>
</div>

<%--Open Accounts Tab--%>

<div id="Open New Acccounts" class="tabcontent">

<form action="OpenBankAccountPath" method="post">

	<h3>Account Opening</h3>
		
	<label>Customer ID :</label><Input type="text" name="customerID" value=<%=pCustomerID %> readonly><br>
	<label>First Name :</label><Input type="text" name="FirstName" value=<%=pCustomerFirstName %> readonly><br>
	<label>Last Name :</label><Input type="text" name="LastName" value=<%=pCustomerLastName %> readonly><br>
	<label>Date of Birth :</label><Input type="text" name="DOB" value=<%=pCustomerDOB %> readonly><br>
	<label>Contact Number :</label><Input type="text" name="ContactNo" value=<%=pCustomerContactNo %> readonly><br>
	<label>Address :</label><Input type="text" name="Address" value=<%=pCustomerAddress %> readonly><br>
	<label>Email :</label><Input type="text" name="Email" value=<%=pCustomerEmail %> readonly><br>
	
	Select Account Type:<br><br>
	<input type="radio" name="Account Type" value="Savings"> Savings Account<br>
 	<input type="radio" name="Account Type" value="Current"> Current Account<br><br>
  	<Input Type="submit" value="Open Account"/>
</form>

</div>


<%--Close Accounts Tab--%>

<div id="Close Account" class="tabcontent">

<form action="CloseBankAccountPath" method="post">

	<h3>Account Closing</h3>
	
	<label>Customer ID :</label><Input type="text" name="customerID" value=<%=pCustomerID %> readonly><br>
	<label>First Name :</label><Input type="text" name="FirstName" value=<%=pCustomerFirstName %> readonly><br>
	<label>Last Name :</label><Input type="text" name="LastName" value=<%=pCustomerLastName %> readonly><br>
	<label>Date of Birth :</label><Input type="text" name="DOB" value=<%=pCustomerDOB %> readonly><br>
	<label>Contact Number :</label><Input type="text" name="ContactNo" value=<%=pCustomerContactNo %> readonly><br>
	<label>Address :</label><Input type="text" name="Address" value=<%=pCustomerAddress %> readonly><br>
	<label>Email :</label><Input type="text" name="Email" value=<%=pCustomerEmail %> readonly><br>
	Select Account:<br><br>
	
	
	<%
	int mAccountNo2;
	DBconnect d12 = new DBconnect();
	Connection con2 = d12.callDB();
	PreparedStatement pstmt2 = con2.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
	pstmt2.setString(1,pCustomerID);
	ResultSet rs2=pstmt2.executeQuery();
	%>
	
	<select name="Account Number">
	<%
	while(rs2.next())
	{
		mAccountNo2=rs2.getInt(2); 
	%>
	<option><%=mAccountNo2%></option>
	
	<%
	}
	%>
	
	<%
	con.close();
	%>
	
	</select><br><br>
  	<Input Type="submit" value="Close Account"/>
</form>

</div>



<script>
	// Creating 3 Tabs

	function BankingAccTab(evt, tabOptions) {
    // Declaring variables
    var i, tabcontent, tablinks;

    // Hide all elements with "tabcontent"
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Change all elements with "tablinks" into blank/inactive status
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // block all tabs and only assign active to current tab
    document.getElementById(tabOptions).style.display = "block";
    evt.currentTarget.className += " active";
	}

	// Choose which tab that has default open and open that
	document.getElementById("defaultOpen").click();
</script>


</body>
</html>