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
            request.getServletContext().getRequestDispatcher("/UserLibrary").forward(request, response);
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
        
        String searchCriteria = request.getParameter("search_criteria");
        List<Song> songs = new ArrayList<Song>();
        
        switch(searchCriteria) {
            case "Song Name":
                songs = Song.getSongsByName(request.getParameter("search_text"));
                break;
            case "Album Name":
                songs = Song.getSongsByAlbumName(request.getParameter("search_text"));
                break;
            case "Songwriter":
                songs = Song.getSongsByAuthor(request.getParameter("search_text"));
                break;
            case "Media Type":
                songs = Song.getSongsByMediaType(request.getParameter("search_text"));
                break;
        }
        
        return songs;
    }
    
    private void setSession(HttpServletRequest request, HttpServletResponse response, List<Song> songs) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        session.setAttribute("songs", songs);
    }
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String url, String msg) 
            throws ServletException, IOException {
        
        request.setAttribute("caption", msg);
        
        request.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
