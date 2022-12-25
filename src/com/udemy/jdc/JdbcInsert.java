package com.udemy.jdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcInsert {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(dbUrl, user, pass);

			System.out.println("Database connection successful!\n");

			// 2.Create a statement
			stmt = con.createStatement();

			// 3.Insert a new Employee
			System.out.println("Inserting a new Employee to database\n");

			int rowAffected = stmt
					.executeUpdate("insert into employees " + "(last_name, first_name, email, department, salary)"
							+ "values " + "('World', 'Hello', 'helo@world.com', 'CEO', 150000.00)");

			// 4.Verify this by getting a list of employees
			rs = stmt.executeQuery("select * from employees order by last_name");
			// 5.Process the result
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + " , " + rs.getString("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}
}
