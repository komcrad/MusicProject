package com.groupProject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Functions {
	
	public static String removeTrailingChar(String s, String charToRemove) {
		if (s.substring(s.length()-1).equals(charToRemove)) {
			return s.substring(0, s.length()-1);
		} else {
			return s;
		}
	}
	
	public static void sendMessage(String messageToSend, String messageName, String redirectString, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> message = new HashMap<String, String>();
		message.put(messageName, messageToSend);
		request.setAttribute("message", message);
		request.getRequestDispatcher(redirectString).forward(request, response);
	}
	
	/*
     * 
     * @param elementName  The name of a group of check boxes or radio buttons
     * @return  Returns a hashmap containing keys that are the names of the elements in the group of inputs that were checked. 
     * The values are just the string "checked"
     */
    public static HashMap<String, String> getCheckGroupHashMap(HttpServletRequest request, String elementName) {
            HashMap<String, String> map = new HashMap<String, String>();
            String[]  array = request.getParameterValues(elementName);
            try {
                    for (String e : array) {
                    	
                            if (e.length() > 0) {
                                    map.put(e, "checked");
                            }
                    }
            } catch (NullPointerException e) {
                    return null;
            }
            return map;
    }

}

