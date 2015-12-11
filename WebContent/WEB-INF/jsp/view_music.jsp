<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Author: Ryan -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view_music.css" type="text/css">
        <title>My Music</title>
    </head>
    <body>
        <header>
            <form id="logout" action="${pageContext.request.contextPath}/com/groupProject/servlets/Logout" method="POST">
                   <input id="logout" type="submit" name="logout" value="Logout">
            </form>
            <h1>Music Library</h1>
        </header>
        <div id="main_content" class="cf">
            <h2>${user.firstName} ${user.lastName}'s Music Collection</h2>
            <div id="search_form">
                <h3>Search Music</h3>
                <form name="search_music" id="search_music" action="${pageContext.request.contextPath}/com/groupProject/servlets/SearchMusic" method="POST">
                    <select name="search_criteria">
                        <option value="Song Name">Song Name</option>
                        <option value="Album Name">Album Name</option>
                        <option value="Songwriter">Songwriter</option>
                        <option value="Media Type">Media Type</option>
                    </select>
                    <input name="search_text" type="text"><br />
                    <input name="search_submit" id="search_music" class="search" type="submit" value="Search">
                    <input name="cancel_search" id="search_search" type="submit" class="cancel" value="Cancel">
                </form>
            </div>
            <div id="user_library">
                <table id="user_songs">
                    <caption>${caption}</caption>
                    <tr>
                        <th>Song Name</th>
                        <th>Album Name</th>
                        <th>Length</th>
                        <th>Songwriter</th>
                        <th>Media Type</th>
                        <th>Remove</th>
                    </tr>
                    <c:forEach var="song" items="${songs}">
                        <tr>
                            <td>${song.name}</td>
                            <td>${song.albumName}</td>
                            <td>${song.length}</td>
                            <td>${song.author}</td>
                            <td>${song.mediaType}</td>
                            <td>
                                <form name="${song.songId}" id="delete_song" name="delete" action="${pageContext.request.contextPath}/com/groupProject/servlets/DeleteSong" method="POST">
                                    <input type="submit" id="delete_song" name="${song.songId}" value="Delete Song">Delete
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="${pageContext.request.contextPath}/WEB-INF/jsp/addSong.jsp">Add Song</a>
        </div>
    </body>
</html>
