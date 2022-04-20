package com.revature.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.revature.model.*;
import com.revature.repo.*;

import ch.qos.logback.classic.Logger;

public class AppUI {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(AppUI.class);
	/**
	 * This method displays the options and is meant to be used in the loginLoop method
	 * to pick an option
	 * @param sc: the scanner object to pass through
	 * @param userSelection will be the number passed through from loginLoop
	 */
	public static int loginInfo(Scanner sc, int userSelection) {
		System.out.print("Please choose an option.\n"
				+ "1) Register an account.\n"
				+ "2) Login to customer accout.\n"
				+ "3) Login as an employee.\n");
		try {
			userSelection = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Sorry, that is not a valid number.");
		}
		sc.nextLine();
		return userSelection;
	}
	
	/**
	 * This loop ensures that the user picks a valid option and returns their option number
	 * @param sc: the scanner object to pass through
	 * @param userSelection: this is the reference to what the user must input
	 * @return
	 */
	public static int loginLoop(Scanner sc, int userSelection) {
		while (0 >= userSelection  | userSelection >= 4 ) {
			userSelection=loginInfo(sc, userSelection);
			if (0 >= userSelection  | userSelection >= 4 ) {
				System.out.println("please choose a numbered option.");
			}
		}
		return userSelection;
	}
	/**
	 * 
	 * @param sc
	 * @return an EmployeeAcc object with the information about the current user
	 */
	public static EmployeeAcc employeeLogin(Scanner sc) {
		EmployeeAcc account=new EmployeeAcc();
		Set<EmployeeAcc> allUsernames=com.revature.repo.PetRepo.employeeUsernames();
		boolean noUser=true;
		String uName="";
		String accountPass="";
		
		
		while (noUser) {
			System.out.println("Type in the main account's username: ");
	
			try {
				uName=sc.next();
			} catch (InputMismatchException e) {
				System.out.println("Sorry, that is not a valid username.");
			}
			sc.nextLine();
			
			for (EmployeeAcc i : allUsernames) {
				//System.out.println(i.getUsername());
				if (i.getUsername().contentEquals(uName)) {
					account=i;
				noUser=false;
				}
			}
			
			if(noUser) System.out.println("That username does not exist, try again.");
		}
		
		boolean wrongPass=true;
		while (wrongPass) {
			System.out.println("Type in the main account's password: ");
			
			try {
				accountPass=sc.next();
			} catch (InputMismatchException e) {
				System.out.println("Sorry, that is not a valid password.");
			}
			sc.nextLine();
			if(account.getPassword().contentEquals(accountPass)) {
				System.out.println("You are now logged in.");
				wrongPass=false;
			} else {
				System.out.println("That password is incorrect");
			}
		}
		logger.info("Employee account with ID: "+account.getId()+" and username: "+account.getUsername()+" logged in.");
		return account;
	}
	
	public static CustomerAcc customerLogin(Scanner sc) {
		int c=0;
		
		while (0 >= c  | c >= 3) {
		System.out.print("Is this a :\n"+
					"1) Main Account \n"+
					"2) Linked Account \n");
			try {
			c=sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Sorry, that is not a valid number.");
			}
		}
		switch (c) {
		case 1:
			CustomerAcc account=new CustomerAcc();
			Set<CustomerAcc> allUsernames=com.revature.repo.PetRepo.customerMainUsernames();
			boolean noUser=true;
			String uName="";
			String accountPass="";
			
			
			while (noUser) {
				System.out.println("Type in the main account's username: ");
		
				try {
					uName=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username.");
				}
				sc.nextLine();
				
				for (CustomerAcc i : allUsernames) {
					//System.out.println(i.getUsername());
					if (i.getUsername().contentEquals(uName)) {
						account=i;
					noUser=false;
					}
				}
				
				if(noUser) System.out.println("That username does not exist, try again.");
			}
			
			boolean wrongPass=true;
			while (wrongPass) {
				System.out.println("Type in the main account's password: ");
				
				try {
					accountPass=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid password.");
				}
				sc.nextLine();
				if(account.getPassword().contentEquals(accountPass)) {
					System.out.println("You are now logged in.");
					wrongPass=false;
				} else {
					System.out.println("That password is incorrect");
				}
			}
			logger.info("Main account with ID: "+account.getId()+" and username: "+account.getUsername()+" logged in.");
			return account;
		case 2:
			CustomerAcc linkedAccount=new CustomerAcc();
			linkedAccount.setMain(false);
			Set<CustomerAcc> allLinkedUsernames=com.revature.repo.PetRepo.customerLinkedUsernames();
			
			boolean w=true;
			String y="";
			String o="";
			
			//////////////////
			while (w) {
				System.out.println("Type in the linked account's username: ");
		
				try {
					y=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username.");
				}
				sc.nextLine();
				
				for (CustomerAcc i : allLinkedUsernames) {
					//System.out.println(i.getUsername());
					if (i.getUsername().contentEquals(y)) {
						linkedAccount=i;
						w=false;
					}
				}
				
				if(w) System.out.println("That username does not exist, try again.");
			}
			
			boolean p=true;
			while (p) {
				System.out.println("Type in the linked account's password: ");
				
				try {
					o=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid password.");
				}
				sc.nextLine();
				if(linkedAccount.getPassword().contentEquals(o)) {
					System.out.println("You are now logged into your linked account.");
					p=false;
				} else {
					System.out.println("That password is incorrect");
				}
			}
			///////////////
			logger.info("Linked account with ID: "+linkedAccount.getId()+" and username: "+linkedAccount.getUsername()+" logged in.");
			return linkedAccount;
			
		default: return new CustomerAcc();
		}
	}
	
	
	public static void registrationLoop (Scanner sc) {
		int userSelection=0;
		
		
		//loops through the process of choosing a option to make sure its correct
		while (0 >= userSelection  | userSelection >= 3) {
			System.out.print("Please select an option\n"
					+"1) link with an existing account\n"
					+ "2)create a new main account.\n");
			
			//checks to make sure the input is an int
			try {
				userSelection = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.print("Sorry, that is not a valid number.\n"
						+ "Please enter a valid option\n");
			}
			if (0 >= userSelection  | userSelection >= 3 ) {
				System.out.println("please choose a numbered option.");
			}
			sc.nextLine();
		}	
		//you need to bring in a set of usernames that have been used for main accounts
		Set<CustomerAcc> allUsernames=com.revature.repo.PetRepo.customerMainUsernames();
		//goes through the process of validating the information for the new linked account
		
		String uName="user1";
		CustomerAcc mainAcc=new CustomerAcc();
		boolean nameIsTaken=true;
		String accountPass="";
		String newPass="";
		float funds=0;
		
		if (userSelection==1) {	
			
			while (nameIsTaken) {
				System.out.println("Type in the main account's username: ");
		
				try {
					uName=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username.");
				}
				sc.nextLine();
				
				for (CustomerAcc i : allUsernames) {
					//System.out.println(i.getUsername());
					if (i.getUsername().contentEquals(uName)) {
						mainAcc=i;
						nameIsTaken=false;
					}
				}
				
				if(nameIsTaken) System.out.println("That username does not exist, try again.");
			}
			
			boolean wrongPass=true;
			while (wrongPass) {
				System.out.println("Type in the main account's password: ");
				
				try {
					accountPass=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid password.");
				}
				sc.nextLine();
				if(mainAcc.getPassword().contentEquals(accountPass)) {
					System.out.println("You may now make a new linked account.");
					wrongPass=false;
				} else {
					System.out.println("That password is incorrect");
				}
			}
			///////////////////////////////////////////////////////////////
			//
			////////////////////////////////////////////////////////////////
			boolean needCredentials=true;
			while (needCredentials) {
				
				try {
					System.out.println("Type in your new account's username: ");
					uName=sc.next();
					sc.nextLine();
					System.out.println("Type in your new account's password: ");
					newPass=sc.next();
					sc.nextLine();
					System.out.println("enter the funds you want in this account;");
					funds=sc.nextFloat();
					
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username or password.");
				}
				
				if (funds<0) {
					System.out.println("You cannot add negative funds");
				} else {
					needCredentials=false;
				}
				
				sc.nextLine();
			}
			////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////
			com.revature.repo.PetRepo.registerLinkedAcc(mainAcc.getId(), uName, newPass, funds);
			
		} else {
			while (nameIsTaken) {
				nameIsTaken=false;
				System.out.println("Type in the main account's username: ");
		
				try {
					uName=sc.next();
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username.");
				}
				sc.nextLine();
				
				for (CustomerAcc i : allUsernames) {
					
					if (i.getUsername().contentEquals(uName)) {
						nameIsTaken=true;
					}
				}
				
				if(nameIsTaken) System.out.println("That username is taken");
			}
			///////////////////////////////////////////////////////////////
			//
			////////////////////////////////////////////////////////////////
			boolean needCredentials=true;
			while (needCredentials) {
				try {
					System.out.println("Type in your new account's password: ");
					newPass=sc.next();
					sc.nextLine();
					System.out.println("enter the funds you want in this account;");
					funds=sc.nextFloat();
				
				} catch (InputMismatchException e) {
					System.out.println("Sorry, that is not a valid username or password.");
				}
				if (funds<0) {
					System.out.println("You cannot add negative funds");
				} else {
					needCredentials=false;
				}
				sc.nextLine();
			}
			////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////
			com.revature.repo.PetRepo.registerAcc(uName, newPass, funds);
			
		}
	}

}





