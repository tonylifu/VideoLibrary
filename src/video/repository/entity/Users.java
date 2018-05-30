package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	private String userName;
	private String name, password, userClass, dept;
	
	public Users(String userName, String name, String password, String userClass, 
			String dept) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.userClass = userClass;
		this.dept = dept;
	}
	public Users() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserClass() {
		return userClass;
	}
	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	
	@Override
	public String toString() {
		return "[Name: "+name+", User Name: "+userName+", "
				+ "User Class: "+userClass+", Dept: "+dept+"]";
	}
	
}
