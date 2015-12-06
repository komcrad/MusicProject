package com.groupProject.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupProject.model.Database;
import com.groupProject.model.Functions;
import com.groupProject.model.User;


@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateUser() {
        super();
       
    }


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
		    	musicPreference.append(string.charAt(0));
		    	musicPreference.append(string + ",");
		    }
	    }
	    User user = new User(email, firstName, lastName, address, city, state, zip, 
	    		musicPreference.toString(), apt, sex.charAt(0), password);
	    HashMap<String, String> errors = user.getUserErrors();
	    if (errors.isEmpty()) {
	    	
	    	// THIS IS ONLY CHANGE FROM SIGNUP SERVLET!!!
	    	Database.updateDatabase(user);
	    	// ******************************************
	    	response.sendRedirect("/");
	    } else {
	    	request.setAttribute("errors", errors);
	    	request.setAttribute("sex", Functions.getCheckGroupHashMap(request, "sex"));
	    	request.setAttribute("musicPreference", Functions.getCheckGroupHashMap(request, "musicPreference"));
	    	request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").include(request, response);
	    }
	}
		
		
		
	}


