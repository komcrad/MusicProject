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
@WebServlet(name = "addSong", description = "Main entry point.", urlPatterns = { "/addSong" })
public class AddsSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddsSong() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	Song songToAdd = new Song();
    	User user = new User();
    	user.getCurrentUser(request);
    	songToAdd.setAlbumName(request.getAttribute("albumName").toString());
    	songToAdd.setName(request.getAttribute("name").toString());
    	songToAdd.setLength(request.getAttribute("length").toString());
    	songToAdd.setAuthor(request.getAttribute("author").toString());
    	songToAdd.setMediaType(request.getAttribute("mediaType").toString());
    	songToAdd.setUser(user);
    	if (!songToAdd.saveSong()){
    		// handle error
    		Functions.sendMessage("Song did not save to Database correctly","DB Error - Song","/WEB-INF/jsp/userLibrary.jsp",request,response);
    	} else {
    		songToAdd.saveSong();
    	}
    	request.getRequestDispatcher("/WEB-INF/jsp/userLibrary.jsp").include(request, response);
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
