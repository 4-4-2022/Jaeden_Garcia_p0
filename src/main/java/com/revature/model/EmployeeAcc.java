package com.revature.model;

public class EmployeeAcc extends AnAccount {
	private String lastName;
	private String firstName;
	private boolean admin;
	
	public EmployeeAcc() {
	}
	
	public EmployeeAcc(int id, String username, String password,String lastName,String firstName,boolean admin) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.lastName=lastName;
		this.firstName=firstName;
		this.admin=admin;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "AdminAcc [lastName=" + lastName + ", firstName=" + firstName + ", admin=" + admin + ", id=" + id
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
}
