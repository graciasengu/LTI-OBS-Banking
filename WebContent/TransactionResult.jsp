<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction record </title>
</head>
<body>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="banking.DBconnect" %>



<% 


//Date pdate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("udate"));
//String pdate=request.getParameter("udate"); 
String pdd=request.getParameter("udd"); 
String pmm=request.getParameter("umm"); 
String pyy=request.getParameter("uyy"); 

String pedd=request.getParameter("uedd"); 
String pemm=request.getParameter("uemm"); 
String peyy=request.getParameter("ueyy"); 

String pacctNum=request.getParameter("Account_Number");
long pacctNum1= Long.parseLong(pacctNum);
String pedate = pedd +"-" +pemm+"-"+peyy;
String pdate = pdd +"-" +pmm+"-"+pyy;
//System.out.println(pdate2);
//out.print("Hello User: You have entered the name: "+pdate); 

java.util.Date pStartdate = null;
java.sql.Date javaSqlDate = null;

java.util.Date penddate = null;
java.sql.Date ejavaSqlDate = null;

pStartdate = new SimpleDateFormat("dd-MM-yyyy").parse(pdate);
javaSqlDate = new java.sql.Date(pStartdate.getTime());


penddate = new SimpleDateFormat("dd-MM-yyyy").parse(pedate);
ejavaSqlDate = new java.sql.Date(penddate.getTime());
//System.out.println(pacctNum);
//System.out.println(pacctNum1);

DBconnect d1 = new DBconnect();
			Connection connection = d1.callDB();

Statement statement = connection.createStatement() ;
PreparedStatement pstmt = connection.prepareStatement("select * from transaction where ACCOUNTNO =? AND TRANSTIMESTAMP BETWEEN ? AND ?");

pstmt.setLong(1, pacctNum1);
pstmt.setDate(2, javaSqlDate);
pstmt.setDate(3, ejavaSqlDate);
int a = pstmt.executeUpdate();
ResultSet resultset = 
	pstmt.executeQuery() ; 


out.print("Number of Records: "+a); 

%>

 


<TABLE BORDER="1">
            <TR>
                <TH>ACCOUNTNO</TH>
                <TH>ACCOUNTTYPE</TH>
                <TH>TRANSACTIONNO</TH>
                <TH>TRANSACTIONTYPE</TH>
                <TH>TRANSACTIONAMT</TH>
                <TH>TRANSTIMESTAMP</TH>
                <TH>RECIPIENTNO</TH>
                <TH>BALANCE</TH>
                 <TH>LOCATION</TH>
 
            </TR>
            <% while(resultset.next()){ %>
            <TR>
                <TD> <%= resultset.getLong(1) %></td>
                <TD> <%= resultset.getString(2) %></TD>
                <TD> <%= resultset.getLong(3) %></TD>
                <TD> <%= resultset.getString(4) %></TD>
                <TD> <%= resultset.getDouble(5) %></TD>
                <TD> <%= resultset.getDate(6) %></TD>
                <TD> <%= resultset.getInt(7) %></TD>
                <TD> <%= resultset.getInt(8) %></TD>
                <TD> <%= resultset.getString(9) %></TD>
            </TR>
            <% } %>
        </TABLE>

<form action="GeneratePDFServPath" method="post">
<input type="hidden" name="pdate" value="<%=pdate%>"/>
<input type="hidden" name=javaSqlDate value="<%=javaSqlDate%>"/>
<input type="hidden" name="pedate" value="<%=pedate%>"/>
<input type="hidden" name="pacctNum" value="<%=pacctNum%>"/>
<Input Type="submit" value="Generate PDF"/>

</form>


  


</body>
</html>