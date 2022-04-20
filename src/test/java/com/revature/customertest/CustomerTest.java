package com.revature.customertest;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.CustomerAcc;
import com.revature.repo.PetRepo;
import com.revature.service.CustomerService;

import uk.org.webcompere.systemstubs.SystemStubs;

	@TestInstance(Lifecycle.PER_CLASS)
	@ExtendWith(MockitoExtension.class)
public class CustomerTest {
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private Set<CustomerAcc> mockCustomerAccounts;
	
	@BeforeAll
	public void setup() {
		/*
		 * Yes, Mockito is annotation-driven, but you do have to tell it to initialize mocks
		 * based on your above annotations.
		 */
		MockitoAnnotations.openMocks(this);
		this.mockCustomerAccounts = new HashSet<>();
		this.mockCustomerAccounts.add(new CustomerAcc(1, "user1", "pass1", 10, true));
		this.mockCustomerAccounts.add(new CustomerAcc(21, "user2", "pass2", 20, true));
		this.mockCustomerAccounts.add(new CustomerAcc(31, "user3", "pass3", 30, true));
		this.mockCustomerAccounts.add(new CustomerAcc(31, "next3", "pass4", 50, false));
		this.mockCustomerAccounts.add(new CustomerAcc(31, "other3", "pass5", 90, false));
		this.mockCustomerAccounts.add(new CustomerAcc(1, "next1", "pass6", 30, false));
		
	}
	 
	
	
}
