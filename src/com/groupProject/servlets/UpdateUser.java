package com.groupProject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupProject.model.Database;
import com.groupProject.model.Functions;
import com.groupProject.model.User;


@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateUser() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	User user = (User) session.getAttribute("user");
		if (user == null) {
			Functions.sendMessage("", "", "/WEB-INF/jsp/index.jsp", request, response);
		}
    	request.getRequestDispatcher("/WEB-INF/jsp/updateUser.jsp").forward(request, response);
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
		    	musicPreference.append(string + ",");
		    }
	    }
	    HttpSession session = request.getSession(true);
	    User user = (User) session.getAttribute("user");
	    user.setFirstName(firstName);
	    user.setLastName(lastName);
	    user.setAddress(address);
	    user.setApt(apt);
	    user.setCity(city);
	    user.setZip(zip);
	    user.setSex(sex.charAt(0));
	    user.setPassword(password);
	    user.setEmail(email);
	    user.setMusicPreference(musicPreference.toString());
	    HashMap<String, String> errors = user.getUserErrors();
	    if (errors.isEmpty()) {
	    	
	    	// THIS IS ONLY CHANGE FROM SIGNUP SERVLET!!!
	    	user.updateUser();
	    	// ******************************************
	    	Functions.sendMessage("Updated user", "header", 
	    			"/WEB-INF/jsp/index.jsp", request, response);
	    } else {
	    	request.setAttribute("errors", errors);
	    	request.setAttribute("sex", Functions.getCheckGroupHashMap(request, "sex"));
	    	request.setAttribute("musicPreference", Functions.getCheckGroupHashMap(request, "musicPreference"));
	    	request.getRequestDispatcher("/WEB-INF/jsp/updateUser.jsp").include(request, response);
	    }
	}
	
		
		
		
	}


