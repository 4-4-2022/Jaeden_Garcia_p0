package com.revature.client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.revature.model.CustomerAcc;
import com.revature.model.EmployeeAcc;

import ch.qos.logback.classic.Logger;

public class EmployeeUI {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(EmployeeUI.class);
	
	public static void employeeDirectory(EmployeeAcc employ, Scanner sc) {
		int choice=0;
		EmployeeAcc employee = employ;
		CustomerAcc customerAcc = null;
		Set<CustomerAcc> all=com.revature.repo.PetRepo.allCustomerInfo();
		
		while (0 >= choice  | choice >= 7 ) {
			all=com.revature.repo.PetRepo.allCustomerInfo();
			System.out.print("How do you want to manage customer accounts? \n"
					+ "1) View account information \n"
					+ "2) Add funds \n"
					+ "3) Remove funds \n"
					+ "4) Change account info \n"
					+ "5) Cancel an acount \n"
					+ "6) Log off and Quit \n");
			
			try {
				choice=sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That is not a valid option.");
			}
			if (0 >= choice  | choice >= 7 ) {
				System.out.println("Please choose a numbered option.");
			}
			sc.nextLine();
			
			if (choice==6) {
				
			}else if (choice==1) {
				 
			}else if(choice>=2 && employee.isAdmin()) {
				
			}else {
			
				System.out.println("You a silly employee youre not an admin and are therefore not allowed to do that. \n"
						+ "You can look but you cant touch! \n");
				choice=0;
			}
			switch (choice) {
			case 0:
				break;
				
			case 1:
				for (CustomerAcc i : all) {
					
					System.out.println("[Account id: "+i.getId()+", Username: "+i.getUsername()+", Password: "+i.getPassword()+", funds: $"+i.getFunds()+"]");
				
				}
				choice=0;
				break;
				
			case 2:
				int accId=0;
				String uName="";
				customerAcc=null;
				System.out.println("What account do you want to add funds to?");
				try {
					System.out.println("Account ID: ");
					accId=sc.nextInt();
					sc.nextLine();
					System.out.println("Account Username: ");
					uName=sc.next();
					
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				for (CustomerAcc i : all) {
					if(accId == i.getId() && uName.contentEquals(i.getUsername())) {
						customerAcc = i;
					}
				}
				
				if(customerAcc==null) {
					System.out.println("ID or Username you input was not matching");
				} else {
					float added=0;
					System.out.println("How much funds to be added: ");
					while (added<=0) {
						
						try {
							added=sc.nextFloat();
							
						} catch (InputMismatchException e) {
							System.out.println("That is not a valid option.");
						}
						sc.nextLine();
						if (added>0) {
						customerAcc.addFunds(added);
						} else if (added < 0) {
							System.out.println("You cannot add a negative number");
						}
					}
				}
				choice=0;
				break;
				
			case 3:
				int acc=0;
				String uNa="";
				customerAcc=null;
				System.out.println("What account do you want to remove funds from?");
				try {
					System.out.println("Account ID: ");
					acc=sc.nextInt();
					sc.nextLine();
					System.out.println("Account Username: ");
					uNa=sc.next();
					
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				
				for (CustomerAcc i : all) {
					if(acc == i.getId() && uNa.contentEquals(i.getUsername())) {
						customerAcc = i;
					}
				}
				
				if(customerAcc==null) {
					System.out.println("ID or Username you input was not matching");
				} else {
					float removed=0;
					System.out.println("How much funds to be removed: ");
					while (removed<=0) {
						
						try {
							removed=sc.nextFloat();
							
						} catch (InputMismatchException e) {
							System.out.println("That is not a valid option.");
						}
						sc.nextLine();
						
						if (removed>0) {
						customerAcc.removeFunds(removed);
						} else if (removed < 0) {
							System.out.println("You cannot remove a negative number");
						}
					}
				}
				choice=0;
				break;
			case 4:
				int acrd=0;
				String un="";
				customerAcc=null;
				CustomerAcc coverCust=null;
				System.out.println("What account do you want to change the info in?");
				try {
					System.out.println("Account ID: ");
					acrd=sc.nextInt();
					sc.nextLine();
					System.out.println("Account Username: ");
					un=sc.next();
					
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				for (CustomerAcc i : all) {
					if(acrd == i.getId() && un.contentEquals(i.getUsername())) {
						customerAcc = i;
					}
				}
				
				if(customerAcc==null) {
					System.out.println("ID or Username you input was not matching");
				}
				else {
					String var="";
					float var1=0;
					String oldUser=customerAcc.getUsername();
					try {
						System.out.println("The current username is ["+customerAcc.getUsername()+"] what will you change it to?");
						var=sc.next();
						customerAcc.setUsername(var);
						sc.nextLine();
						System.out.println("The current password is ["+customerAcc.getPassword()+"] what will you change it to?");
						var=sc.next();
						customerAcc.setPassword(var);
						sc.nextLine();
						System.out.println("The current funds are ["+customerAcc.getFunds()+"] what will you change it to?");
						var1=sc.nextFloat();
						customerAcc.setFunds(var1);
					} catch (InputMismatchException e) {
						System.out.println("That is not a valid option.");
					}
					sc.nextLine();
						
					com.revature.repo.PetRepo.updateCustomerUsername(customerAcc, oldUser);
						
				}
				choice=0;
				break;
				
			case 5:
				int crd=0;
				String n="";
				customerAcc=null;
				System.out.println("What account do you want to DELETE?");
				try {
					System.out.println("Account ID: ");
					crd=sc.nextInt();
					sc.nextLine();
					System.out.println("Account Username: ");
					n=sc.next();
					
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				for (CustomerAcc i : all) {
					if(crd == i.getId() && n.contentEquals(i.getUsername())) {
						customerAcc = i;
					}
				}
				
				if(customerAcc==null) {
					System.out.println("ID or Username you input was not matching");
				} else {
					com.revature.repo.PetRepo.deleteCustomer(customerAcc);
				}
				choice=0;
				break;
			case 6:
				System.out.println("Good Bye employee! I hope you didn't break anything :D");
				logger.info("(ID:"+ employee.getId()+", Username:"+ employee.getUsername()+") logged off.");
				System.exit(0);
			}
		}
	}
}
