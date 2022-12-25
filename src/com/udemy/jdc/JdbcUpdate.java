package com.udemy.jdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.ResultsetRow;

public class JdbcUpdate {
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "student";
		String pass = "student";

		try {
			// get a connection to database
			con = DriverManager.getConnection(dbUrl, user, pass);

			// create statement
			stmt = con.createStatement();

			// call helper method to display the employee's info
			System.out.println("BEFORE THE UPDATE...");
			displayEmployee(con, "Wel", "Doe");

			// UPDATE the employee
			System.out.println("\nEXECUTING THE UPDATE FOR : John Doe\n");

			int rowAffected = stmt.executeUpdate("update employees " + "set email='john.doe@hotmail.com' "
					+ "where last_name='Doe' and first_name='John'");

			// call helper method to display the employee's information
			System.out.println("AFTER THE UPDATE....");
			displayEmployee(con, "John", "Doe");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
	}

	private static void displayEmployee(Connection con, String firstName, String lastName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(
					"select last_name, first_name, email from employees where last_name=? and first_name=?");
			pstmt.setString(1, lastName);
			pstmt.setString(2, firstName);

			// Execute SQL query
			rs = pstmt.executeQuery();

			// process result set
			while (rs.next()) {
				String theLastName = rs.getString("last_name");
				String theFirstName = rs.getString("first_name");
				String email = rs.getString("email");

				
				System.out.println(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt, rs);
		}
	}

	private static void close(Connection con, Statement stmt, ResultSet rs) throws SQLException {
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

	private static void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);
	}
}