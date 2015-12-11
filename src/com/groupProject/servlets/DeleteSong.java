package com.groupProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupProject.model.Song;

/**
 * Servlet implementation class DeleteSong
 */
@WebServlet("/DeleteSong")
public class DeleteSong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSong() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	Song songToDelete = new Song();
    	songToDelete.deleteSongById((int) session.getAttribute("song_ID"));
    	request.getRequestDispatcher("/WEB-INF/jsp/userLibrary.jsp").include(request, response);
	}

}
