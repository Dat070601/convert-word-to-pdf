<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
background-image: url("https://cutewallpaper.org/21/website-background-images-hd/Autodesk-Wallpaper-Website-Background-Hd-Wallpapers-.jpg");
background-size: 100%;
}
form {
color: Black;
}
a {
color: Black;
}
h1{
color: Black;
}
</style>
</head>
<body align="center">
	<h1 align="center">Login</h1>
	<form action="checkLogin" method= "post">
		Username: <input type="text" name="username"><p>
		Password: <input type="password" name="password"><p>
	<input type="submit" value="Login">
	</form>
</body>
</html>