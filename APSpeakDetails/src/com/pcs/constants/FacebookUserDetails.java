package com.pcs.constants;


@SuppressWarnings("static-access")

public class FacebookUserDetails {
	
	public static String userName="";
	public static String firstName="";
	public static String lastName="";
	public static String token="";
	public static String gender="";
	public static String location="";
	public static String email="";

	public static String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public static String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public static String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public static String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public static String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public static String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public static String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
