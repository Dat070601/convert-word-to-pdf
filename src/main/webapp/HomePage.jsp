<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.*,Model.BEAN.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
body {
background-image: url("https://cdn.trumcongnghe.com/2021/08/119234579_169457918065236_755533166268046471_n-1-1140x570.jpg");
background-size: 100%;
}
table{
color: White;
}
h1 {
color: White;
}
h3 {
color: White;
}
file {
color: White;
}
</style>
</head>
<body align="center">
<% User user = (User)request.getSession().getAttribute("User"); %>
<h1 align="center">Xin chào <%= user.getUsername() %>!!</h1>
	<form action ="UploadFileServlet" method = "post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="Tải Lên">
	</form>
<p><h3>Các file của bạn</h3></p>
<table border="1" align="center" width=100%>   
   <tr>
      <th width=80%>Tên Tệp</th>
      <th>Trạng Thái</th>
   </tr>
   <tr>
   	  <th>file pdf test</th>
   	  <th><a href="DownloadFileServlet?id=1">Download</a></th>
   </tr>
</table>

</body>
</html>