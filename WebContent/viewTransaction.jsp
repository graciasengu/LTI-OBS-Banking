<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="banking.CustomerL"%> 
    
 <%@ page import="java.sql.*"
		  import="java.text.*"
		  import="banking.DBconnect" %>
		  
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

<div class="tab">
  <button class="tablinks" onclick="BankingAccTab(event, 'View Accounts')" id="defaultOpen" >View Accounts</button>
  <button class="tablinks" onclick="BankingAccTab(event, 'Open New Acccounts')">Open New Accounts</button>
  <button class="tablinks" onclick="BankingAccTab(event, 'Close Account')">Close Accounts</button>
</div>


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
			    </div>
		 </tr>
	 	 </table>
			          
		<div style="display:block;">
		<%
			int mAccountNo;
		 	DBconnect d1 = new DBconnect();
			Connection con = d1.callDB();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Accounts WHERE customerid =?");
			pstmt.setString(1,mCustomerID);
			ResultSet rs=pstmt.executeQuery();
		%>
					
		<select name="Account_Number">
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
		
		<table style="width:100%">
		<input type="submit" value="Next"/>
		</table>
		
		</div>
				          
	
	</form>
	</div>


<div id="Open New Acccounts" class="tabcontent">

<form action="OpenBankAccountPath" method="post">
	<label>Login Username :</label><Input type="text" name="customerID"><br>
	<label>Registered Email :</label><Input type="text" name="regEmail"><br>
	<Input Type="submit" value="SUBMIT"/>
</form>

</div>

<div id="Close Account" class="tabcontent">
  <p>close</p>
</div>

<script>
	function BankingAccTab(evt, tabOptions) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabOptions).style.display = "block";
    evt.currentTarget.className += " active";
	}

	// Get the element with id="defaultOpen" and click on it
	document.getElementById("defaultOpen").click();
</script>


</body>
</html>