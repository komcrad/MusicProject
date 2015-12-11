package com.groupProject.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupProject.model.Song;

/**
 *
 * @author Ryan Newman
 */
@WebServlet(urlPatterns = {"/SearchMusic"})
public class SearchMusic extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if(request.getParameter("search_submit") != null) {
            searchMusic(request, response);
        }
        else {
            request.getServletContext().getRequestDispatcher("/com/groupProject/servlets/UserLibrary").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        processRequest(request, response);
    }
    
    private void searchMusic(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String url = "/WEB-INF/jsp/view_music.jsp";
        String msg = "";
        int count = 0;
        
        List<Song> songs = searchSongs(request, response);
        
        if(songs.isEmpty()) {
            msg = "No results found";
        }
        else {
            count = songs.size();
            msg = "Query yielded " + count + " results";
            setSession(request, response, songs);
        }
        
        forward(request, response, url, msg);
    }
    
    private List<Song> searchSongs(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Song> songs = User.getUserSongs(user);
        List<Song> results = new ArrayList<Song>();
        String searchCriteria = request.getParameter("search_criteria");
        String search_text = request.getParameter("search_text");
        
        switch(searchCriteria) {
            case "Song Name":
                results = this.getSongsByName(songs, search_text);
                break;
            case "Album Name":
                results = this.getSongsByAlbumName(songs, search_text;
                break;
            case "Songwriter":
                results = this.getSongsByAuthor(songs, search_text;
                break;
            case "Media Type":
                results = this.getSongsByMediaType(songs, search_text);
                break;
        }
        
        return results;
    }
    
    private void setSession(HttpServletRequest request, HttpServletResponse response, List<Song> songs) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        session.setAttribute("songs", songs);
    }
    
    private List<Song> getSongsByName(List<Song> songs, String search_text ) {
        
        List<Song> results = new ArrayList<Song>();
        
        for(Song song: songs) {
            if(song.getName().contains(search_text) {
                results.add(song);
            }
        }
        
        return results;
    }
    
    
    private List<Song> getSongsByAlbumName(List<Song> songs, String search_text ) {
        
        List<Song> results = new ArrayList<Song>();
        
        for(Song song: songs) {
            if(song.getAlbumName().contains(search_text) {
                results.add(song);
            }
        }
        
        return results;
    }
    
    
    private List<Song> getSongsByAuthor(List<Song> songs, String search_text ) {
        
        List<Song> results = new ArrayList<Song>();
        
        for(Song song: songs) {
            if(song.getAuthor().contains(search_text) {
                results.add(song);
            }
        }
        
        return results;
    }
    
    
    private List<Song> getSongsByMediaType(List<Song> songs, String search_text ) {
        
        List<Song> results = new ArrayList<Song>();
        
        for(Song song: songs) {
            if(song.getMediaType().contains(search_text) {
                results.add(song);
            }
        }
        
        return results;
    }
    
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String url, String msg) 
            throws ServletException, IOException {
        
        request.setAttribute("caption", msg);
        
        request.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
