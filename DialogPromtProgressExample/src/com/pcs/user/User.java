package com.pcs.user;



public class User {
	private String email;
	private String phoneNumber;
	private String userName;
	private String timePicker;
	private String datePicker;



	public String getTimePicker() {
		return timePicker;
	}
	public void setTimePicker(String timePicker) {
		this.timePicker = timePicker;
	}
	public String getDatePicker() {
		return datePicker;
	}
	public void setDatePicker(String datePicker) {
		this.datePicker = datePicker;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}