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
            <form id="logout" action="Logout" method="POST">
                   <input id="logout" type="submit" name="logout" value="Logout">
            </form>
            <h1>Music Library</h1>
        </header>
        <div id="main_content" class="cf">
            <h2>${user.firstName} ${user.lastName}'s Music Collection</h2>
            <h2>${message.message}</h2>
            <div id="search_form">
                <h3>Search Music</h3>
                <form name="search_music" id="search_music" action="/SearchMusic" method="POST">
                    <select name="search_criteria">
                        <option value="Song Name">Song Name</option>
                        <option value="Album Name">Album Name</option>
                        <option value="Songwriter">Songwriter</option>
                        <option value="Media Type">Media Type</option>
                    </select>
                    <input name="search_text" type="text"><br />
                    <input name="search_submit" id="search_music" class="search" type="submit" value="Search">
                </form>
                
                <form action="/UserLibrary" method="POST" name="cancel_search" id="cancel_search">
                	<input type="submit" name="cancel_search" value="Cancel" id="cancel_search">
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
                        <th>Delete Song</th>
                    </tr>
                    <c:forEach var="song" items="${songs}">
                        <tr>
                            <td>${song.name}</td>
                            <td>${song.albumName}</td>
                            <td>${song.length}</td>
                            <td>${song.author}</td>
                            <td>${song.mediaType}</td>
                            <td>
                                <form onsubmit="return confirm('Are you sure you want to delete this song?');" name="delete_song" id="delete_song" name="delete" action="/DeleteSong" method="POST">
                                    <input type="hidden" name="song_id" value="${song.songId}">
                                    <input type="submit" id="delete_song" name="delete_song" value="Remove">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <a href="/AddSong">Add Song</a> <a href="/UpdateUser">Update User Info</a>
        </div>
    </body>
</html>
