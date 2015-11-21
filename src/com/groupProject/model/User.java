package com.groupProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName, sex;
	@Column(nullable=false)
	private String password;
	
	public User(){}

	public User(String userName, String password, String sex){
		setPassword(password);
		setUserName(userName);
		setSex(sex);
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return userId+","+userName;
	}

	
}
