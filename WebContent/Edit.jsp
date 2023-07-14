<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<%
int id = Integer.parseInt(request.getParameter("id"));
%>
	<h2>EDIT THE DATA</h2>
    <form action="Edit" enctype = "multipart/form-data" method="post">
    <input type="hidden" name="id" value="<%=id%>">
        <div class="form-group">
          <label>Title</label>
          <input type="text" class="form-control" name="Title">
        </div>
        <div class="form-group">
          <label>Quantity</label>
          <input type="text" class="form-control" name="Quantity">
        </div>
        <div class="form-group">
            <label >Size</label>
            <input type="text" class="form-control" name="Size">
        </div>
        <div>
            <label>Image</label>
            <input type="file" accept="image/png, image/jpg" name="Image">
            <input type="submit" value="Edit"> 
        </div>
      </form>
</body>
</html>