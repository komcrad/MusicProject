<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../style/mystyle.css">
	<title>Login</title>
</head>
<body>
	<h1>Login to Server</h1>
	
	<form action="/signup" method="post">
		Email: <input type="text" name="email" class="textInput" required> <br>
		Password: <input type="password" name="password" class="textInput" required> <br>
		<input type="submit" value="login" name="login"><br>
	</form>
	<a href="Signup">Signup</a>
</body>
</html>
