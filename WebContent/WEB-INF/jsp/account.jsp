<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Successful.  Now Add Some Code.</h1>
	<table>
		<tr>
			<th>User Information</th>
		</tr>
		<tr>
			<td>${user.userId}</td>
		</tr>
		<tr>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td>${user.firstName}</td>
		</tr>
		<tr>
			<td>${user.lastName}</td>
		</tr>
		<tr>
			<td>${user.address}</td>
		</tr>
		<tr>
			<td>${user.city}</td>
		</tr>
		<tr>
			<td>${user.state}</td>
		</tr>
		<tr>
			<td>${user.zip}</td>
		</tr>
		<tr>
			<td>${user.musicPreference}</td>
		</tr>
		<tr>
			<td>${user.password}</td>
		</tr>
		<tr>
			<td>${user.apt}</td>
		</tr>
		<tr>
			<td>${user.sex}</td>
		</tr>
	</table>
</body>
</html>