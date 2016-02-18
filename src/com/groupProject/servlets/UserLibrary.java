package com.groupProject.servlets;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupProject.model.Song;
import com.groupProject.model.User;

/**
 *
 * @author Ryan
 */
@WebServlet(urlPatterns = {"/UserLibrary"})
public class UserLibrary extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	if (User.isLoggedIn(request)) {
	        response.setContentType("text/html;charset=UTF-8");
	        
	        String url = "/WEB-INF/jsp/view_music.jsp";
	        String caption = "My Library";
	
	        HttpSession session = request.getSession(false);
	        User user = (User) session.getAttribute("user");
	        
	
	        List<Song> songs = User.getUserSongs(user);
	        
	        session.setAttribute("songs", songs);
	        session.setAttribute("caption", caption);
	        
	        request.getServletContext().getRequestDispatcher(url).forward(request, response);
    	} else {
    		response.sendRedirect("/");
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
}
