package com.groupProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.Session;

@Entity
public class User {
	@Id
	@Column(nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String firstName, lastName, address,  city, state, zip, musicPreference, password;
	
	@Column(nullable=true)
	private String apt;
	
	@Column(nullable=false)
	private char sex;
	
	public User(){}

	public User(String email, String firstName, String lastName, String address, String city, String state, String zip,
			String musicPreference, String apt, char sex, String password) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.musicPreference = musicPreference;
		this.apt = apt;
		this.sex = sex;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	
	public String getMusicPreference() {
		return musicPreference;
	}
	/**
	 * Sets the musicPreference variable
	 * @param musicPreference  a comma delimited string that looks like "Rock,Classic,Jazz,Country,Pop,Alternative,Rap" or
	 * something similar with fewer options. NO SPACES ALLOWED
	 */
	public void setMusicPreference(String musicPreference) {
		this.musicPreference = musicPreference;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	/**
	 * validates the user and returns a detailed response in hashmap form
	 * empty if all user data is valid
	 * @return hashmap of errors specifiying what is wrong with the user's data
	 */
	public HashMap<String, String> getUserErrors() {
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!this.getFirstName().matches("^[a-zA-Z0-9]*$")) {
			errors.put("firstName", "First name may only contain alphanumeric characters");
		}
		if (!this.getLastName().matches("^[\\w'-\\.]+$")) {
			errors.put("lastName", "Last name must only contain alphanumeric characters plus ' and -");
		}
		if (!this.getEmail().matches("^[_A-Za-z0-9\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.put("email", "Email must be a valid email");
		}
		
		//test password
		{
			String specials = "-,‘,!,@,#,$,%,^,&,*,(,),{,},[,],?";
			String[] specialChars = specials.split(",");
			boolean result = false;
			for (int i = 0; i < specialChars.length; i++) {
				if (this.getPassword().contains(specialChars[i])) {
					result = true;
					break;
				}
			}
			if (!(this.getPassword().matches(".*[a-z]+.*") && this.getPassword().matches(".*[A-Z]+.*") &&
					this.getPassword().matches(".*[-‘!@#$%^&*(){}\\[\\]?]+.*")) || this.getPassword().length() < 8) {
				errors.put("password", "invalid password");
				System.out.println(this.getPassword().matches(".*[a-z]+.*"));
				System.out.println(this.getPassword().length() < 8);
				System.out.println(!result);
			}
		}
		
		if(!this.getCity().matches("[a-zA-Z]+") || this.getCity().length() < 1) {
			errors.put("city", "City must only contain alpha characters and must contain at least 1 letter");
		}
		if (!this.getState().matches("[A-Z]+") || this.getState().length() != 2) {
			errors.put("state", "State must contain the two uppercase letter abreviation for the state");
		}
		if (!this.getZip().matches("^[0-9]{5}(?:-[0-9]{4})?$")) {
			errors.put("zip", "Zip must contain 5 of the form #####, or 9 numeric characters of the form #####-####");
		}
		List<String> options = Arrays.asList("Rock,Classic,Jazz,Country,Pop,Alternative,Rap".split(","));
		Arrays.asList(this.getMusicPreference().split(",")).forEach((e) -> {
			if (!options.contains(e)) {
				errors.put("musicPrefrences", "That was not an option");
				return;
			}
		});
		
		if (this.getSex() != 'M' && this.getSex() != 'F') {
			errors.put("sex", "Sex must be 'M' or 'F'");
		}
		return errors;
	}

	/**
	 * Returns a comma-delimited representing the user's property values
	 * @return String representing the user object
	 */
	@Override
	public String toString() {
		return userId + "," + email + "," + firstName + "," + lastName + "," + address + "," + city + "," + state
				+ "," + zip + "," + musicPreference + "," + apt + "," + sex + "," + password;
	}
	
	/**
	 * Validates that the user data is valid and saves the user to the database if the user is valid
	 * @return  true if User is saved, false if user fails.
	 */
	public boolean saveUser() {
		if (this.getUserErrors().isEmpty()) {
			if (Database.saveToDatabase(this)) {
				return true;
			} else {
				System.out.println("Save failed");
				return false;
			}
		}
		System.out.println("User had errors");
		return false;
	}

	public static User getUserByEmail(String email) {
		String hql = "from User u where u.email = '"+email+"'";
		User user = (User) Database.runQuery(hql).get(0);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Song> getUserSongs(User user) {
		String hql = "from Song where user_userId = "+user.getUserId();
		return Database.runQuery(hql);
	}
	//Tests the functionality of this test
	public static void main(String[] args) {
//		User user = new User("bob@gmail.com", "Bob", "Yakman", "1111 Nowhere St.", "Pretendville", 
//				"FL", "62221", "Rock,Pop", "", 'M', "PASSWoRD!");
//		HashMap<String, String> hm = user.getUserErrors();
//		System.out.println(hm.get("firstName"));
//		System.out.println(hm.get("lastName"));
//		System.out.println(hm.get("email"));
//		System.out.println(hm.get("city"));
//		System.out.println(hm.get("state"));
//		System.out.println(hm.get("zip"));
//		System.out.println(hm.get("musicPrefrences"));
//		System.out.println(hm.get("sex"));
//		System.out.println(hm.get("password"));
//		user.saveUser();
//		user = null;
//		User user = getUserByEmail("bob@gmail.com");
//		Song song = new Song("Dangerous", "Listen", "04:29", "David Guetta", "Computer", user);
//		song.saveSong();
//		String hql = "from User u where u.email = 'bob@gmail.com'";
//		User user = getUserByEmail("bob@gmail.com");
//		user.setEmail("useAHoe@gmail.com");
//		System.out.println(user.getEmail());
//		System.out.println(Database.updateDatabase(user));
		
//		System.out.println(user.toString());
//		User.getUserSongs(user).forEach(e -> {
//			System.out.println(e.toString());
//		});
//		Database.kill();
	}
	
}
