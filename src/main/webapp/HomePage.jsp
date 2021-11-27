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
background-image: url("https://cutewallpaper.org/21/website-background-images-hd/Autodesk-Wallpaper-Website-Background-Hd-Wallpapers-.jpg");
background-size: 100%;
}
table{
color: Black;
}
h1 {
color: Black;
}
h3 {
color: Black;
}
file {
color: Black;
}

</style>
</head>
<body>
<a href="CheckLogout" style="background-color: #555;
  							 color: White;
  							 padding: 0.5em 1em;
  							 text-decoration: none;
  							 float: right;">Logout</a>
<% User user = (User)request.getSession().getAttribute("User"); %>
<h1 align="center">Xin chào <%= user.getUsername() %>!!</h1>
	<form action ="UploadFileServlet" method = "post" enctype="multipart/form-data" align="center">
		<input type="file" name="file">
		<input type="submit" value="Tải Lên">
	</form>
<p><h3 align="center">Các file của bạn</h3></p>
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
<form action="Checklogout" method="post">
</form>
</body>
</html>