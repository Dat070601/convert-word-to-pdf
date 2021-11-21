<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.*" %>
    <%@ page import ="Model.BEAN.Wife" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>hello</h1>
<table border="1">
<%
	ArrayList<Wife> wifeArray = (ArrayList<Wife>)request.getAttribute("wifeArray");
	for(int i=0; i<wifeArray.size(); i++)
	{
		%>
		<tr>
		<td><%= wifeArray.get(i).getName()%></td>
		<td><%= wifeArray.get(i).getAddress()%></td>
		<td><%= wifeArray.get(i).isAlive()%></td>
		</tr>
	<% } %>
</table>
</body>
</html>