package com.groupProject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupProject.model.Functions;
import com.groupProject.model.User;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").include(request, response);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String apt = request.getParameter("apt");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String sex = request.getParameter("sex");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String[] musicPreferences = request.getParameterValues("musicPreference");
        StringBuilder musicPreference = new StringBuilder();
	    if (musicPreferences != null) {
	        for(String string : musicPreferences) {
		    	musicPreference.append(string + ",");
		    }
	    }
	    User user = new User(email, firstName, lastName, address, city, state, zip, 
	    		musicPreference.toString(), apt, sex.charAt(0), password);
	    HashMap<String, String> errors = user.getUserErrors();
	    if (errors.isEmpty()) {
	    	user.saveUser();
	    	response.sendRedirect("/");
	    } else {
	    	request.setAttribute("errors", errors);
	    	request.setAttribute("sex", Functions.getCheckGroupHashMap(request, "sex"));
	    	request.setAttribute("musicPreference", Functions.getCheckGroupHashMap(request, "musicPreference"));
	    	request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").include(request, response);
	    }
	}

}
