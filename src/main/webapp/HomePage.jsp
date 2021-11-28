<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import ="java.util.*,Model.BEAN.User,Model.BEAN.File" %>
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
				margin-left: 5%;
			}
			h3 {
				color: Black;
			}
			file {
				color: Black;
			}	
			body {
				text-align: center;
			}
			.button {
				background-color: #555;
				color: White;
				padding: 0.5em 1em;
				text-decoration: none;
				float: right;
			}
			.button-refresh {
				background-color: ghostwhite;
				color: black;
				padding: 0.5em 1em;
				text-decoration: none;
				float: right;
			}
		</style>
	</head>
	<body>
		<%
			User user = (User)request.getSession().getAttribute("User");
			if (user == null) {
		%>
			<h1><b>BẠN CHƯA CÓ TÀI KHOẢN</b></h1>
		<%
			} else {
		%>
		<a href="logout" class="button">Logout</a>
		<h1>Xin chào <%= user.getUsername() + " " %>!</h1>
		<h3>Tải lên file Word</h3>
		<form action ="UploadFileServlet" method = "post" enctype="multipart/form-data">
			<input type="file" name="file">
			<input type="submit" value="Tải Lên">
		</form>
		<%
			String message = (String)request.getAttribute("message");
			String messageColor = "red";
			if (message != null) {
				switch (message) {
					case "extension error":
						message = "Định dạng file không đúng !";
						break;
					case "upload error":
						message = "Lỗi khi tải file lên !";
						break;
					case "success":
						message = "Đã tải file lên !";
						messageColor = "green";
						break;
					case "file not found":
						message = "Không tìm thấy file !";
						break;
					case "user invalid":
						message = "Bạn không có quyền tải file này !";
						break;
					case "processing":
						message = "File vẫn đang được xử lý !";
						messageColor = "yellow";
						break;
				}
		%>
			<p style="color: <%=messageColor%>"><%=message%></p>
		<%
			}
		%>
		<h3 align="center">Các file của bạn</h3>
		<a href="LoadFileServlet" class="button-refresh">Refresh</a>
		<table border="1" align="center" width=100%>   
		   <tr>
		      <th width=70%>Tên Tệp</th>
		      <th>Ngày tải lên</th>
		      <th>Trạng Thái</th>
		   </tr>
		   <tr>
		   <%
		   		ArrayList<File> l = (ArrayList<File>)request.getAttribute("UserFiles");
		   		if (l == null) {		
		   %>
		   		<tr><td colspan="3" style="text-aglin: center"><h2>Bạn chưa có file nào</h2></td></tr>
		   <%
		   		} else {
		   			for(File f: l) {	   			
		   %>
		   			<tr>
			   			<td><%=f.getFileName()%></td>
			   			<td><%=f.getDate()%></td>
		   <%
		   				if (f.getStatus()) {
		   %>
		   					<td><a href="DownloadFileServlet?id=<%=f.getFileID()%>">Download</a></td>
		   <%
		   				} else {
		   %>
		   					<td>File chưa xử lý xong</td>
		   <%
		   				}
		   %>
		   			</tr>
		   <%
		   			}
		   		}
		   %>
		</table>
		<%
			}
		%>
	</body>
</html>