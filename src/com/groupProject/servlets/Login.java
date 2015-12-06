package com.groupProject.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.groupProject.model.Database;
import com.groupProject.model.Functions;
import com.groupProject.model.User;

/**
 * Servlet implementation which handles authentication of a user.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    		User user = User.getUserByEmail(request.getParameter("email"));
    		if (user == null || !user.getPassword().equals(request.getParameter("password"))) { // Log in unsuccessful.
    			Functions.sendMessage("Login failed", 
    					"header", "/WEB-INF/jsp/index.jsp", request, response);
    		} else { // Log in successful.
    			HttpSession session = request.getSession(true);
    			session.setAttribute("user", user);
    			session.setMaxInactiveInterval(60*10);
    			
    			request.getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(request, response);
    		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return A String containing the servlet description.
	 */
	@Override
	public String getServletInfo() {
		return "Handles authentication of user when attempting to log in.";
	}

}
