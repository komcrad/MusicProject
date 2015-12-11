<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.groupProject.model.User"%>

<%
	if (session == null) {
		String address = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
%>

<jsp:useBean id="user" class="com.groupProject.model.User"
	scope="session"></jsp:useBean>
<jsp:setProperty name="user" property="*" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update User Information</title>
</head>
<body>
<body>


	<h1>Update User:</h1>
	<form action="UpdateUser" method="post">
			
			First Name*: <input name="firstName" type="text" value="<jsp:getProperty name="user" property="firstName"/>" required><strong>${errors.firstName} </strong><br>
		 
			Last Name*: <input name="lastName"	type="text" value="<jsp:getProperty name="user" property="lastName"/>" required><strong>${errors.lastName}</strong><br> 
			
			Password*: <input name="password" type="password" value="<jsp:getProperty name="user" property="password"/>" required> <strong> ${errors.password} </strong><br> 
			
			Street Address: <input name="address" value="<jsp:getProperty name="user" property="address"/>" type="text"><strong>	${errors.address} </strong><br>
			 
			Apt Number: <input name="apt" value="<jsp:getProperty name="user" property="apt"/>" type="text"><br> 
			
			City: <input name="city" type="text" value="<jsp:getProperty name="user" property="city"/>"><strong>	${errors.city} </strong><br> 
			
			State: <input name="state" type="text" 	value="<jsp:getProperty name="user" property="state"/>"><strong> ${errors.state} </strong><br>
			
			Zip Code: <input name="zip" type="text" value="<jsp:getProperty name="user" property="zip"/>"><strong>${errors.zip} </strong><br> 
			
			Email Address*: <input name="email"	type="text" value="<jsp:getProperty name="user" property="email"/>" required> <strong>${errors.email} </strong><br>
			
			<%
			boolean userSex = false;
			User u = new User();
			char sex = u.getSex();
			String temp = Character.toString(sex);
				if (temp.equals("M")){
					userSex = true;}
			%>
			
			<strong> ${errors.sex} </strong><br>
			Sex*: 
				<label>Male</label><input name="sex" type="radio" value="M" ${sex.M} <%if (userSex = true){ %> checked <% }%>> 
				<label>Female</label><input name="sex"	type="radio" value="F" ${sex.F} <%if (userSex = false){ %> checked <% }%>><br> 
				
				
			Music Preferences*:
				Rock <input name="musicPreference" type="checkbox" value="Rock"	${musicPreference.Rock}> 
				Classic <input name="musicPreference" type="checkbox" value="Classic" ${musicPreference.Classic}> 
				Jazz <input	name="musicPreference" type="checkbox" value="Jazz"	${musicPreference.Jazz}> 
				Country <input name="musicPreference" type="checkbox" value="Country" ${musicPreference.Country}> 
				Pop <input name="musicPreference" type="checkbox" value="Pop" ${musicPreference.Pop}>
				Alternative <input name="musicPreference" type="checkbox" value="Alternative" ${musicPreference.Alternative}> 
				Rap <input	name="musicPreference" type="checkbox" value="Rap"	${musicPreference.Rap}>
				<strong> ${errors.musicPreferences} </strong><br> 
				<input type="submit" value="Update Account" id='submit'>
	</form>
</body>

</body>
</html>