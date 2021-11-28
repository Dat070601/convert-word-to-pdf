<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			h1{
				color: Black;
			}
			.center {
				text-align: center;
			}
			.button {
				margin-top: 10px;
				margin-left: 5px;
			}
		</style>
	</head>
	<body align="center">
		<h1 align="center">Login</h1>
		<form action="login" method= "post">
			<table style="border: 0px; margin-left: auto; margin-right: auto">
				<tr>
					<td>Username: </td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" class="center">
						<input class="button" type="submit" value="Login" />
						<input class="button" type="reset" value="Reset" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>