package com.nea.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetExp {
	static final String jdbcDriver = "com.nea.jdbc.example.ResultSetExp";
	static final String dbUrl = "jdbc:postgresql://localhost:5432/emp";
	static final String user = "postgres";
	static final String pass = "postgres";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;

	public static void main(String[] args) {

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbUrl, user, pass);
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			String sql = "SELECT id, first, last, age FROM employees";
			ResultSet resultSet = statement.executeQuery(sql);

			//
			System.out.println("List result set for reference....");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int age = resultSet.getInt("age");
				String first = resultSet.getString("first");
				String last = resultSet.getString("last");

				System.out.print("id: " + id);
				System.out.print(", age: " + age);
				System.out.print(", first: " + first);
				System.out.println(", last: " + last);
			}

			//
			resultSet.beforeFirst();
			// STEP 7: Extract data from result set
			while (resultSet.next()) {
				// Retrieve by column name
				int newAge = resultSet.getInt("age") + 5;
				resultSet.updateDouble("age", newAge);
				resultSet.updateRow();
			}
			System.out.println("List result set showing new ages...");
			// Insert a record into the table.
			// Move to insert row and add column data with updateXXX()
			System.out.println("Inserting a new record...");
			resultSet.moveToInsertRow();
			resultSet.updateInt("id", 104);
			resultSet.updateString("first", "John");
			resultSet.updateString("last", "Paul");
			resultSet.updateInt("age", 40);
			// Commit row
			resultSet.insertRow();

			System.out.println("List result set showing new set...");
			printRs(resultSet);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void printRs(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int age = resultSet.getInt("age");
				String first = resultSet.getString("first");
				String last = resultSet.getString("last");

				System.out.print("id: " + id);
				System.out.print(", age: " + age);
				System.out.print(", first: " + first);
				System.out.println(", last: " + last);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
