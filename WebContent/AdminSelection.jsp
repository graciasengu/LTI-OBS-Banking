<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*"%>
<%@ page import="banking.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>


div.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    
}

/* Style the buttons inside the tab */
div.tab button {
   
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: none;
    border-top: none;
    
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Selection</title>
</head>
<body>
<% //to allow java coding

//Emp e1 = new Emp();

int pEmpID = 0; //setting an empty variable to hold Customer ID later 

pEmpID = Emp.getmEmpID();

//gracia
/*
Cookie pUserCookie;
Cookie[] pCookieArray = request.getCookies(); // creating an array of cookies for current domain

if(pCookieArray !=null) // if there are cookies present inside the Customer's computer
{
	for (int i = 0; i < pCookieArray.length; i++) 
	{
		pUserCookie = pCookieArray[i]; // Assigning new array data to cookie object for each iteration of the loop
	
		if(pUserCookie.getName().equals("employeeid")) // checking if the data from the array matches with input infomation from Customer
		{
			pEmpID = pUserCookie.getValue(); // transfer the CustomerID from the cookie to the variable so it can be used 
		}
	}
}
*/
//if customer ID is not available it will automatically redirect to login page
if( pEmpID == 0) 
{%>
	<script>
	alert(" You need to Log In!");
	window.location = 'loginpage.jsp';
	</script>
<%
	
}
%>

<div class="tab">
  <button class="tablinks" onclick="openRequest(event, 'OPEN')">OPEN ACC</button>
  <button class="tablinks" onclick="openRequest(event, 'DEBIT')">DEBIT</button>

</div>

<div id="OPEN" class="tabcontent">

<%
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
PreparedStatement stmt=con.prepareStatement("SELECT * FROM Request WHERE (requesttype=? OR  requesttype=? )AND APPSTATUS = 'Pending'");
stmt.setString(1,"Savings");
stmt.setString(2,"Current");
ResultSet rs=stmt.executeQuery();
%>
<table cellpadding="15" border="1">
<tr>
<td>CUSTOMERID</td>
<td>REQUESTNO</td>
<td>REQUESTTYPE</td>
<td>TIMEOFREQUEST</td>
<td>APPSTATUS</td>
<td>REQUESTSTATUS</td>
<td>Verified</td>
<td>Incomplete</td>
<td>REMARKS</td>
</tr>
<% while(rs.next())
{ %>	
<tr>
<td><%= rs.getString(1)%></td>
<td><%= rs.getLong(2)%></td>
<td><%= rs.getString(3)%></td>
<td><%= rs.getTimestamp(4)%></td>
<td><%= rs.getString(5)%></td>
<td><%= rs.getString(6)%></td>

<td>  
       <form action="Admin_newacc_Verified" method="post">  
            <input type="submit" name = "uVerified" value="Verified" >
             <input type="hidden" name = "uhidVerified" value="<%= rs.getLong(2)%>" >
        </form>
</td>
<td>
 <form action="Admin_newacc_Incomplete" method="post">
             <input type="submit" name = "uIncomplete" value="Incomplete" >
             <input type="hidden" name = "uhidIncomplete" value="<%= rs.getLong(2)%>" >
              <input type="hidden" name = "uhidID" value="<%= rs.getString(1)%>" >
             </td>
              <td><select name="uremark">
        
            <option value="Not Enough Money"> Not Enough Money</option>
            <option value="Suspect Fake IC">Suspect Fake IC</option>
             <option value="Wrong address">Wrong address</option>
             
             </select>
        </form></td>
</tr>


<%
}
%>
</table>

</div>

<div id="DEBIT" class="tabcontent">
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
stmt=con.prepareStatement("SELECT * FROM Request WHERE requesttype=? AND APPSTATUS = 'Pending'");
stmt.setString(1,"Apply ATM Card");
rs=stmt.executeQuery();
%>
<table cellpadding="15" border="1">
<tr>
<td>CUSTOMERID</td>
<td>REQUESTNO</td>
<td>REQUESTTYPE</td>
<td>TIMEOFREQUEST</td>
<td>APPSTATUS</td>
<td>REQUESTSTATUS</td>
<td>Verified</td>
<td>Incomplete</td>
<td>REMARKS</td>
</tr>
<% while(rs.next())
{ %>	


<tr>
<td><%= rs.getString(1)%></td>
<td><%= rs.getLong(2)%></td>
<td><%= rs.getString(3)%></td>
<td><%= rs.getTimestamp(4)%></td>
<td><%= rs.getString(5)%></td>
<td><%= rs.getString(6)%></td>

<td>
<FORM Action="Admin_atmreq_Verified" method="post">
<p><input type="submit" name="Verified" value="Verified"></p>
  <input type="hidden" name = "uhidVerified" value="<%= rs.getLong(2)%>" >
  
</FORM></td>

<td>
<FORM Action="Admin_atmreq_Incomplete" method="post">
<p><input type="submit" name="Incomplete" value="Incomplete"></p>
 <input type="hidden" name = "uhidIncomplete" value="<%= rs.getLong(2)%>" >
 <input type="hidden" name = "uhidID" value="<%= rs.getString(1)%>" >
 </td>
 <td>
  <select name="uremark">
        
            <option value="Not Enough Money"> Not Enough Money</option>
            <option value="Suspect Fake IC">Suspect Fake IC</option>
             <option value="Wrong address">Wrong address</option>
  </select>
  
</FORM></td>
</tr>



<%
}
%>
</table>
</div>	


</table>


<script>
function openRequest(evt, requestName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(requestName).style.display = "block";
    evt.currentTarget.className += " active";
}
</script>

</body>
</html>