package com.revature.client;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.revature.model.CustomerAcc;

import ch.qos.logback.classic.Logger;

public class CustomerUI {
	//CustomerAcc customerAccount;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomerUI.class);
	
	public static void customerDirectory(CustomerAcc customerAccount, Scanner sc) {
		int choice=0;
		CustomerAcc customerAcc=customerAccount;
		Set<CustomerAcc> all=com.revature.repo.PetRepo.allCustomerInfo();
		while (0 >= choice  | choice >= 6 ) {
			all=com.revature.repo.PetRepo.allCustomerInfo();
			System.out.print("How do you want to manage these funds? \n"
					+ "1) View funds \n"
					+ "2) Add funds \n"
					+ "3) Remove funds \n"
					+ "4) Transfer funds to a different account \n"
					+ "5) Log off and Quit \n");
			try {
				choice=sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That is not a valid option.");
			}
			if (0 >= choice  | choice >= 6 ) {
				System.out.println("Please choose a numbered option.");
			}
			sc.nextLine();
		
			switch (choice) {
			
			case 0:
				break;
				
			case 1:
				System.out.println("This accout username is "+customerAcc.getUsername()+" and has $"+customerAcc.getFunds());
				
				for (CustomerAcc i : all) {
					if(i.getId()==customerAcc.getId()) {
						System.out.println("[Username: "+i.getUsername()+", funds: $"+i.getFunds()+"]");
					}
				}
				choice=0;
				break;
			case 2:
				System.out.println("How much funds do you want to add?");
				float adding=0;
				
				try {
					adding=sc.nextFloat();
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				
				if(adding==0) {
					System.out.println("Nothing was added.");
				} else {
					customerAcc.addFunds(adding);
				}
				
				choice=0;
				break;
				
			case 3:
				System.out.println("How much funds do you want to remove?");
				float taking=0;
				
				try {
					taking=sc.nextFloat();
				} catch (InputMismatchException e) {
					System.out.println("That is not a valid option.");
				}
				sc.nextLine();
				
				if(taking==0) {
					System.out.println("Nothing was removed.");
				} else {
					customerAcc.removeFunds(taking);
				}
				choice=0;
				break;
				
			case 4:
				Set<CustomerAcc> otherAcc=new HashSet<>();
				Set<String> useNames= new HashSet<>();
				
				for (CustomerAcc i : all) {
					if(i.getId()==customerAcc.getId()) {
						otherAcc.add(i);
					}
				}
				
				System.out.println("These are your options:");
				
				for (CustomerAcc i : otherAcc) {
					if (i.getUsername().equals(customerAcc.getUsername())) {
					} else {
						useNames.add(i.getUsername());
						System.out.println("[Username: "+i.getUsername()+" , Funds= $"+i.getFunds()+"]");
					}
				}
				
				if(useNames.size()==0) {
					System.out.println("you have no connected accounts");
					choice=0;
					break;
				}
				String uName="";
				
				while (uName.equals("")) {
					System.out.println("Choose a connected account username:");
					try {
						uName=sc.next();
					} catch (InputMismatchException e) {
						System.out.println("That is not a valid option.");
					}
					sc.nextLine();
					if (useNames.contains(uName)) {
					} else {
						System.out.println("That username is not an option");
						uName="";
					}
				}
				
				CustomerAcc other=new CustomerAcc();
				
				for (CustomerAcc i : otherAcc) {
					if (i.getUsername().equals(uName)) {
						other=i;
					}
				}
				
				float transfer=0;
				while (transfer==0) {
					System.out.println("how much do you want to transer?");
					try {
						transfer=sc.nextFloat();
					} catch (InputMismatchException e) {
						System.out.println("That is not a valid option.");
					}
					sc.nextLine();
				}
				customerAcc.transerFunds(other, transfer);
				
				choice=0;
				break;
				
			case 5:
				System.out.println("Thank you for using our services!");
				logger.info("(ID:"+ customerAcc.getId()+", Username:"+customerAcc.getUsername()+") logged off.");
				System.exit(0);
			}
		}
	}
	
	public static void viewFunds(CustomerAcc customerAccount) {
		
	}
}
