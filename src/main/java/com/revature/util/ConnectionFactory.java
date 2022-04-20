package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		Connection conn=null;
		try  {
		conn=DriverManager.getConnection(System.getenv("revaturep0_db_url"),
				System.getenv("revaturep0_db_user"),
				System.getenv("revaturep0_db_pass"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
