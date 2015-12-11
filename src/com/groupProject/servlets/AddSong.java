package com.groupProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.groupProject.model.*;

/**
 * Servlet implementation class index
 */
@WebServlet("/AddSong")
public class AddSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSong() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Song songToAdd = new Song();
    	User user = User.getCurrentUser(request);
    	songToAdd.setAlbumName(request.getParameter("albumName").toString());
    	songToAdd.setName(request.getParameter("songName").toString());
    	songToAdd.setLength(request.getParameter("length").toString());
    	songToAdd.setAuthor(request.getParameter("author").toString());
    	songToAdd.setMediaType(request.getParameter("mediaType").toString());
    	songToAdd.setUser(user);
    	if (!songToAdd.saveSong()){
    		// handle error
    		request.setAttribute("errors", songToAdd.getSongErrors());
    		request.getRequestDispatcher("/WEB-INF/jsp/addSong.jsp").forward(request, response);
    	} else {
    		response.sendRedirect("/UserLibrary");
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (User.isLoggedIn(request)) {
			request.getRequestDispatcher("/WEB-INF/jsp/addSong.jsp").include(request, response);
		} else {
			request.getRequestDispatcher("/").forward(request, response);
		}
	}
	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return A String containing the servlet description.
	 */
	@Override
	public String getServletInfo() {
		return "Main entry point in to the application.";
	}

}
