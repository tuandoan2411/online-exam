package com.tuandoan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/mythitracnghiem?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "12345";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);

			Connection myConn =
					DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful!!! TTTTTTTT");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}



