package com.revature.model;

import org.slf4j.LoggerFactory;

import com.revature.client.AppUI;

import ch.qos.logback.classic.Logger;

public class CustomerAcc extends AnAccount{
	protected float funds;
	protected boolean isMain=true;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(AppUI.class);
	
	public CustomerAcc() {
	}
	
	public CustomerAcc(int id, String username, String password, float funds, boolean isMain) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.funds=funds;
		this.isMain=isMain;
	}
	
	public float getFunds() {
		return funds;
	}
	public void setFunds(float funds) {
		this.funds = funds;
	}
	
	
	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	@Override
	public String toString() {
		return "CustomerAcc [funds=" + funds + ", id=" + id + ", username=" + username + ", password=" + password +
				" ,isMain="+isMain+"]";
	}

	public boolean addFunds(float amountAdded) {
		if (amountAdded < 0) {
			System.out.println("You cannot add negative amounts. Funds remaind the same");
			return false;
		} else {
			this.funds+=amountAdded;
			
			com.revature.repo.PetRepo.updateCustomer(this);
			logger.info("$"+amountAdded+" funds were added");
			return true;
		}
	}
	
	public boolean removeFunds(float amountRemoved) {
		if(amountRemoved<0) {
			System.out.println("You cannot remove negative amounts. Funds remaind the same");
			return false;
		}
		else if (this.funds-amountRemoved < 0) {
			System.out.println("You cannot take this amount out of this account. Funds remaind the same");
			return false;
		}
		else { 
			this.funds-=amountRemoved;
			com.revature.repo.PetRepo.updateCustomer(this);
			logger.info("$"+amountRemoved+" funds were removed");
			return true;
		}
	}
	
	public boolean transerFunds(CustomerAcc other, float transferAmount) {
		if (this.funds-transferAmount < 0) {
			System.out.println("You cannot take this amount out of this account. Funds remaind the same");
			return false;
		} else if (other.funds+transferAmount < 0) {
			System.out.println("You cannot take this amount out of the other account. Funds remaind the same");
			return false;
		}
		else { 
			if (transferAmount <0) {
				transferAmount=transferAmount*-1;
				this.addFunds(transferAmount);
				other.removeFunds(transferAmount);
			}else {
				this.removeFunds(transferAmount);
				other.addFunds(transferAmount);
			}
			
			System.out.println("Your funds have been transferred");
		}
		return true;
	}
	
}





