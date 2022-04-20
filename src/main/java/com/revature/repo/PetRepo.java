package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.LoggerFactory;

import com.revature.model.Animal;
import com.revature.model.CustomerAcc;
import com.revature.model.EmployeeAcc;
import com.revature.model.Pet;
import com.revature.util.ConnectionFactory;
import com.revature.util.ResourceCloser;

import ch.qos.logback.classic.Logger;

public class PetRepo {
	private static PetRepo petRepo;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(PetRepo.class);
	
	private PetRepo() {
		super();
	}
	
	public static PetRepo getPetRepo() {
		if(petRepo == null) {
			petRepo = new PetRepo();
		}
		return petRepo;
	}
	
	public Set<Pet> findAllPets() {
		Set<Pet> pets = new HashSet<>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet set=null;
		final String SQL = "SELECT * FROM pets";
		
		try {
			conn=ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				pets.add(new Animal(
						set.getString(1),
						set.getString(2),
						set.getString(3),
						set.getBoolean(4),
						set.getInt(5),
						set.getString(6),
						set.getFloat(7)));
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		return pets;
	}
	
	public void save(Pet animal) {
		Connection conn=null;
		PreparedStatement stmt = null;
		final String SQL = "insert into pets values(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, animal.getGroup());
			stmt.setString(2, animal.getFamily());
			stmt.setString(3, animal.getBreed());
			stmt.setBoolean(4, animal.getIsMale());
			stmt.setInt(5, animal.getAge());
			stmt.setString(6, animal.getNature());
			stmt.setFloat(7, animal.getCost());
			stmt.execute();
			logger.info("The Animal ("+animal+") was added to table pets");
		}catch(SQLException e) {
			e.printStackTrace();
			logger.info("An exeption did not allow ("+animal+") to be inserted into table pets");
		}finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		}
	}
	
	public static void updateCustomer(CustomerAcc customer) {
		Connection conn=null;
		PreparedStatement stmt = null;
		String SQL = "";
		if (customer.isMain()) {
			SQL="update main_accounts set acc_id = ? , username = ? , user_password = ? , funds = ? "
					+ "where acc_id = " +customer.getId() + ";";
			try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getUsername());
			stmt.setString(3, customer.getPassword());
			stmt.setFloat(4, customer.getFunds());
			stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be updated");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}else {
			SQL="update linked_accounts set acc_id = ? , username = ? , user_password = ? , funds = ? "
					+ "where acc_id = " +customer.getId() + " and username = ? ;";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, customer.getId());
				stmt.setString(2, customer.getUsername());
				stmt.setString(3, customer.getPassword());
				stmt.setFloat(4, customer.getFunds());
				stmt.setString(5, customer.getUsername());
				stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be updated");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}
		
		
		logger.info("Accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
		+") was successfully updated");
	}
	
	public static void updateCustomerUsername(CustomerAcc customer, String oldUser) {
		Connection conn=null;
		PreparedStatement stmt = null;
		String SQL = "";
		if (customer.isMain()) {
			SQL="update main_accounts set acc_id = ? , username = ? , user_password = ? , funds = ? "
					+ "where acc_id = " +customer.getId() + ";";
			try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getUsername());
			stmt.setString(3, customer.getPassword());
			stmt.setFloat(4, customer.getFunds());
			stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be updated");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}else {
			SQL="update linked_accounts set acc_id = ? , username = ? , user_password = ? , funds = ? "
					+ "where acc_id = " +customer.getId() + " and username = ? ;";
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, customer.getId());
				stmt.setString(2, customer.getUsername());
				stmt.setString(3, customer.getPassword());
				stmt.setFloat(4, customer.getFunds());
				stmt.setString(5, oldUser);
				stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be updated");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}
		
		
		logger.info("Accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
		+") was successfully updated (old username was: "+oldUser);
	}
	
	public static void deleteCustomer(CustomerAcc customer) {
		Connection conn=null;
		PreparedStatement stmt = null;
		String SQL = "";
		if (customer.isMain()) {
			SQL="delete from main_accounts where acc_id = " +customer.getId() + " ;";
			
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be updated");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}else {
			SQL="delete from linked_accounts where acc_id = " +customer.getId() + " and username = ? ;";
			
		
		
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, customer.getUsername());
				stmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
				logger.info("An exeption did not allow accout (ID:"+customer.getId()+", Username:"+customer.getUsername()
							+") to be deleted");
			}finally {
				ResourceCloser.closeConnection(conn);
				ResourceCloser.closeStatement(stmt);
			}
		}
		logger.info("Account (ID:"+customer.getId()+", Username:"+customer.getUsername()
		+") was successfully Deleted");
	}
	
	public static Set<CustomerAcc> customerMainUsernames() {
		Set<CustomerAcc> customers=new HashSet<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet set=null;
		final String SQL = "SELECT * FROM main_accounts";
		try {
			conn=ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				customers.add(new CustomerAcc(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getFloat(4),
						true));
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		
		return customers;
	}
	
	public static Set<CustomerAcc> customerLinkedUsernames() {
		Set<CustomerAcc> customers=new HashSet<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet set=null;
		final String SQL = "SELECT * FROM linked_accounts";
		try {
			conn=ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				customers.add(new CustomerAcc(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getFloat(4),
						false));
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		
		return customers;
	}
	
	public static Set<CustomerAcc> allCustomerInfo() {
		Set<CustomerAcc> allCustomers=new HashSet<>();
		Set<CustomerAcc> mainCustomers=customerMainUsernames();
		Set<CustomerAcc> linkedCustomers=customerLinkedUsernames();
		for (CustomerAcc i : mainCustomers) {
			allCustomers.add(i);
		}
		
		for (CustomerAcc i : linkedCustomers) {
			allCustomers.add(i);
		}
		return allCustomers;
	}
	
	public static Set<EmployeeAcc> employeeUsernames() {
		Set<EmployeeAcc> employee=new HashSet<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet set=null;
		final String SQL = "SELECT * FROM employee_accounts";
		try {
			conn=ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			set = stmt.executeQuery(SQL);
			while(set.next()) {
				employee.add(new EmployeeAcc(
						set.getInt(1),
						set.getString(2),
						set.getString(3),
						set.getString(4),
						set.getString(5),
						set.getBoolean(6)));
			}
		}	catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeResultSet(set);
			ResourceCloser.closeStatement(stmt);
		}
		
		return employee;
	}
	
	public static void registerAcc(String username, String password, float funds) {
		Connection conn=null;
		PreparedStatement stmt = null;
		final String SQL = "insert into main_accounts values(default, ?, ?, ?)";
		
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setFloat(3, funds);
			stmt.execute();
		 } catch (SQLException e ) {
			 e.printStackTrace();
		 } finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
			
		 }
		logger.info("A new main account with the username: "+username+", was created.");
	}
	
	public static void registerLinkedAcc(int id, String username, String password, float funds) {
		Connection conn=null;
		PreparedStatement stmt = null;
		final String SQL = "insert into linked_accounts values(?, ?, ?, ?)";
		 try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setFloat(4, funds);
			stmt.execute();
		 } catch (SQLException e ) {
			 e.printStackTrace();
		 } finally {
			ResourceCloser.closeConnection(conn);
			ResourceCloser.closeStatement(stmt);
		 }
		 logger.info("A new linked account with the username: "+username+", was created.");
	}
	
}





