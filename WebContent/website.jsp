<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,com.advancejava.Details,org.hibernate.*,
org.hibernate.boot.registry.StandardServiceRegistryBuilder,
org.hibernate.cfg.*,
org.hibernate.service.ServiceRegistry, java.util.*, com.servlet.Addproduct"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<title>E-commerce</title>
  	<link rel="stylesheet" href="webstyle.css">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" 
    integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
	 if(session.getAttribute("username") == null){
		response.sendRedirect("login.jsp");
	} 
%>
    <div class="formcont">
    
      <div class="header">
        <h2>Product Management Tool</h2>
        <h4>Please enter product details to add to stock</h4>
      </div>
        <div class="container">
        <form action="Addproduct" method="post" enctype = "multipart/form-data">
            <label for="">Title</label>	
            <input type="text" name="Title" required>
            <br><br>
            <label for="">Quantity</label>
            <input type="text" name="Quant" pattern="\d+" required>
            <br><br>
            <label for="">Size</label>
            <input type="text" name="Size" pattern="\d+" required>
            <br><br>
            <label for="">Image</label>
            <input type="file" accept="image/png, image/jpg" name="Image">
            <input type="submit" value="Add">    
     	</form>
     </div>
    <form action="Logout">
      <input type="submit" value="Logout">
    </form>
  </div>
    
    <%  
      Object obj = request.getAttribute("detailsList");
      List<Details> list = null;
      if(obj != null && obj instanceof List) {
          list = (List<Details>) obj;
      }
      %>
    <table class="table table-dark table-striped">
        <thead>
          <tr>
            <th scope="col">S.No</th>
            <th scope="col">Title</th>
            <th scope="col">Quantity</th>
            <th scope="col">Size</th>
            <th scope="col">Image</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
    <% if(list != null) { %>
      <% for(int i=0; i<list.size(); i++) {String imageConvert = Base64.getEncoder().encodeToString(list.get(i).getImage()); %>
        <tr>
          <td><%= i+1 %></td>
          <td><%= list.get(i).getTitle() %></td>
          <td><%= list.get(i).getQuantity() %></td>
          <td><%= list.get(i).getSize() %></td>
          <td><img alt="ProductImage" style="max-height: 300px; max-width: 200px; width: auto;"
						src="data:image/png;base64,<%=imageConvert%>"
						class="card-img-top m-2"></td>
		  <td><form action="Delete" enctype="multipart/form-data" method="post">
          <input type="hidden" name="id" value="<%= list.get(i).getID()%>">
          <button type="submit" name="delete"><i class="fa-solid fa-trash"></i></button>
        </form>
        <form action="Edit.jsp" method="post" >
        <input type="hidden" name="id" value="<%= list.get(i).getID()%>">
          <button type="submit" name="edit"><i class="fa-solid fa-pen-to-square"></i></button>
        </form></td>
        </tr>
      <% } %>
    <% } %>
  </tbody>
      </table>
     
</body>
</html>