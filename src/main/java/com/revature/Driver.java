package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import com.revature.model.*;

public class Driver {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int userSelection=0;
		int loginChoice=0;
		AnAccount account;
		
		//this gets the login choice and prompts the user to create or log in as a customer or employee
		loginChoice=com.revature.client.AppUI.loginLoop(sc, userSelection);
		
		//this while loop lest the user create more accounts and decide when they want to log in
		//after creating or if they want to create more they can
		while (loginChoice==1) {
			com.revature.client.AppUI.registrationLoop(sc);
			loginChoice=0;
			loginChoice=com.revature.client.AppUI.loginLoop(sc, loginChoice);
		}
		
		if(loginChoice==2) {
			//customer log in
			CustomerAcc custIn=com.revature.client.AppUI.customerLogin(sc);
			com.revature.client.CustomerUI.customerDirectory(custIn, sc);
		} else {
			//employee log in
			EmployeeAcc employIn=com.revature.client.AppUI.employeeLogin(sc);
			com.revature.client.EmployeeUI.employeeDirectory(employIn, sc);
		}
		
	}
	
}
