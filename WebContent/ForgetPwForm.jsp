<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
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
	<form action="ForgetPasswordServletPath" method="post">
	
	<label>Login Username :</label><Input type="text" name="ucustomerID"><br>
	<label>Registered Email :</label><Input type="text" name="uregEmail"><br>
	<Input Type="submit" value="SUBMIT"/>
	
	</form>
</body>
</html>