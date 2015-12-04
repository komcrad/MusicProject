<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create Account</title>
</head>
<body>
	<body>
	<h1>${message.header}</h1>
	<form action="Signup" method="post">
		First Name*: <input name="firstName" type="text" value="${param.firstName}" required><strong> ${errors.firstName} </strong><br>
		Last Name*: <input name="lastName" type="text"  value="${param.lastName}" required><strong> ${errors.lastName} </strong><br>
		Password*: <input name="password" type="password"  value="${param.password}" required> <strong> ${errors.password} </strong><br>
		Street Address: <input name="address" value="${param.address}" type="text"><strong> ${errors.address} </strong><br>
		Apt Number: <input name="apt" value="${param.apt}" type="text"><br>
		City: <input name="city" type="text" value="${param.city}"><strong> ${errors.city} </strong><br>
		State: <input name="state" type="text" value="${param.state}"><strong> ${errors.state} </strong><br>
		Zip Code: <input name="zip" type="text" value="${param.zip}"><strong> ${errors.zip} </strong><br>
		Email Address*: <input name="email" type="text" value="${param.email}" required> <strong> ${errors.email} </strong><br>
		<strong> ${errors.sex} </strong><br>
		Sex*:
		<label>Male</label><input name="sex" type="radio" value="M" ${sex.M} required>
		<label>Female</label><input name="sex" type="radio" value="F" ${sex.F}><br>
		Music Preferences*: 
		Rock <input name="musicPreference" type="checkbox" value="Rock" ${musicPreference.Rock}>
		Classic <input name="musicPreference" type="checkbox" value="Classic" ${musicPreference.Classic}>
		Jazz <input name="musicPreference" type="checkbox" value="Jazz" ${musicPreference.Jazz}>
		Country <input name="musicPreference" type="checkbox" value="Country" ${musicPreference.Country}>
		Pop <input name="musicPreference" type="checkbox" value="Pop" ${musicPreference.Pop}>
		Alternative <input name="musicPreference" type="checkbox" value="Alternative" ${musicPreference.Alternative}>
		Rap <input name="musicPreference" type="checkbox" value="Rap" ${musicPreference.Rap}><strong> ${errors.musicPreferences} </strong><br>
		<input type="submit" value="Create Account" id='submit'>
	</form>
</body>

</body>
</html>
