<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<h3>${message.DBErrorSong}</h3>
<title>Add Song</title>
</head>
<body>
	<form action="AddSong" method="post">
		Song Name*: <input name="songName" type="text" value="${param.songName}" required><strong> ${errors.songName} </strong><br>
		Album Name*: <input name="albumName" type="text" value="${param.albumName}" required><strong> ${errors.albumName} </strong><br>
		Length*: <input name="length" type="text" value="${param.length}" required><strong> ${errors.length} </strong><br>
		Author*: <input name="author" type="text" value="${param.author}" required><strong> ${errors.author} </strong><br>
		Media Type*: <select name="mediaType" required>
						<option value="Computer">Computer</option>
						<option value="MP3 Player">MP3 Player</option>
						<option value="CD">CD</option>
						<option value="Vinyl">Vinyl</option>
					</select><strong> ${errors.mediaType} </strong><br>	
		<input type="submit">						
	</form>
	<a href="/UserLibrary">Library</a>
</body>
</html>