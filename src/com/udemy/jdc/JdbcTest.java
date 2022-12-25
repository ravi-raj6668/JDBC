package com.udemy.jdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
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

			// 3.Execute SQL query
			rs = stmt.executeQuery("select * from employees");

			// 4.Process the result
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
