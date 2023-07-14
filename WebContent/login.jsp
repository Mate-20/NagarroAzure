<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=my, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css"> 
</head>
<body>
<%
    String errorMsg = (String)session.getAttribute("errorMsg");
    if (errorMsg != null) {
%>
        <div class="error-msg" style="background-color: rgb(253, 168, 168); max-width: 250px; padding: 10px; border-radius: 6px; margin-bottom: 10px;">
            <%=errorMsg%>
        </div>
<%
        session.removeAttribute("errorMsg");
    }
%>
<form action="Login" method="post">
        <h3>Login</h3>
        
        <div class="container">
            <label for="">Username</label>
            <input type="text" name="uname">
            <br><br>
            <label for="">Password</label>
            <input type="password" name="pass">
            <br>
        </div>
        <div class="submitbutton">
            <input type="submit" value="Login" class="submit">
        </div> 
    </form>
    
</body>
</html>