package com.groupProject.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.groupProject.model.Database;
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
    	Session session = Database.getNewSession();
    	
    	try {
    		Criteria criteria = session.createCriteria(User.class)
    				.add(Restrictions.eq("email", request.getParameter("email")))
    				.add(Restrictions.eq("password", request.getParameter("password")));
    		
    		@SuppressWarnings("unchecked")
			List<User> results = criteria.list();
    		
    		if (results.isEmpty()) { // Log in unsuccessful.
    			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    		} else { // Log in successful.
    			this.getServletContext().setAttribute("user", results.get(0));
    			
    			request.getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(request, response);
    		}
    	} catch (HibernateException e) {
    		e.printStackTrace();
    	} finally {
    		session.close();
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
